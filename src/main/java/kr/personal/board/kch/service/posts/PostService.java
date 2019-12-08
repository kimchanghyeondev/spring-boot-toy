package kr.personal.board.kch.service.posts;

import kr.personal.board.kch.handler.error.Custom_Bad_REQUEST;
import kr.personal.board.kch.web.domain.posts.Posts;
import kr.personal.board.kch.web.domain.posts.PostsRepository;
import kr.personal.board.kch.web.dto.PostListResponseDto;
import kr.personal.board.kch.web.dto.PostResponseDto;
import kr.personal.board.kch.web.dto.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class PostService {
    private final PostsRepository postsRepository;


    public Long save(PostSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    public void update(Long id, PostSaveRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(("해당 사용자가 없습니다..")));
        posts.update(requestDto.getTitle(), requestDto.getContent());


    }

    public PostResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new Custom_Bad_REQUEST(id + "번에 해당하는 게시글이 없습니다"));
        return new PostResponseDto(posts);
    }

    public List<PostListResponseDto> findAll() {
        return postsRepository.findAll()
                .stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());

    }

    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new Custom_Bad_REQUEST(id + "번에 해당하는 게시글이 없습니다"));
        postsRepository.delete(posts);
    }
}