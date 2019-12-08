package kr.personal.board.kch.web.domain.posts;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.personal.board.kch.service.posts.PostService;
import kr.personal.board.kch.web.dto.PostResponseDto;
import kr.personal.board.kch.web.dto.PostSaveRequestDto;
import kr.personal.board.kch.web.util.ResponseEntityUtilsInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class PostApiController {
    private final PostService postService;
    private final ResponseEntityUtilsInterface responseEntityUtils;

    @PostMapping("/api/v1/posts")
    public String save(@RequestBody PostSaveRequestDto requestDto) throws JsonProcessingException {
        String message = "정상적으로 글이 등록되었습니다.";
        postService.save(requestDto);
        return responseEntityUtils.responseEntityToString(HttpStatus.CREATED,message);
    }

    @PutMapping("/api/v1/posts/{id}")
    public String update(@RequestBody PostSaveRequestDto requestDto, @PathVariable("id") Long id)throws JsonProcessingException  {
        String message = "정상적으로 수정 되었습니다";
        postService.update(id, requestDto);
        return responseEntityUtils.responseEntityToString(HttpStatus.CREATED,message);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public String delete(@PathVariable("id") Long id) throws JsonProcessingException {
        String message = "정상적으로 삭제 되었습니다!";
        postService.delete(id);
        return responseEntityUtils.responseEntityToString(HttpStatus.NO_CONTENT,message);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostResponseDto findById(@PathVariable("id") Long id) {
        return postService.findById(id);
    }


}
