package kr.personal.board.kch.web.domain;


import kr.personal.board.kch.web.domain.posts.Posts;
import kr.personal.board.kch.web.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // 별다른 설정이 없을경우 h2 db를 사용한다.
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanUp(){
        postsRepository.deleteAll();
    }

    @Test
    public void savePostTest() throws Exception {
        //given
        String title = "제목";
        String content = "내용";
        Posts post = postsRepository.save(Posts.builder().title(title).content(content).author("author").build());
        //when
        Posts posts = postsRepository.findById(post.getId()).orElseThrow(Exception::new);
        assertThat(posts.getTitle()).isEqualTo(title);
        //then
    }

    @Test
    public void auditingTest(){
        //given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                .author("author")
                .content("content")
                .title("title")
                .build());
        //when
        Posts posts = postsRepository.findAll().get(0);

        //then
        assertThat(posts.getCreateTime()).isAfter(now);
    }
}