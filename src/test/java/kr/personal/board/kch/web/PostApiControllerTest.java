package kr.personal.board.kch.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import kr.personal.board.kch.web.domain.posts.Posts;
import kr.personal.board.kch.web.domain.posts.PostsRepository;
import kr.personal.board.kch.web.dto.PostSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    ObjectMapper objectMapper;

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void postsSave() throws Exception {
        //given
        String title = "title";
        String content = "content";
        PostSaveRequestDto requestDto = PostSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void postsUpdate()throws Exception{
        //given
        Posts savedPost = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("kimch")
                .build());
        Long savedPostId = savedPost.getId();
        String modifiedTitle = "modifiedTitle";
        String modifiedAuthor = "kimchanghyeon";

        PostSaveRequestDto willModifyEntity = PostSaveRequestDto.builder()
                .title(modifiedTitle)
                .author(modifiedAuthor)
                .content(savedPost.getContent())
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/"+savedPostId;

        HttpEntity<PostSaveRequestDto> requestEntity = new HttpEntity<>(willModifyEntity);


        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,requestEntity,Long.class);


        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(postsRepository.findById(savedPostId).get().getTitle()).isEqualTo(modifiedTitle);
    }


}