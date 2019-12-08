package kr.personal.board.kch.web.domain;

import kr.personal.board.kch.config.auth.dto.SessionUser;
import kr.personal.board.kch.service.posts.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class indexController {

    private final PostService postService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postService.findAll());
        if (httpSession.getAttribute("user") != null) {
            SessionUser user = (SessionUser) httpSession.getAttribute("user");
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postSave() {
        return "post-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable("id") Long id, Model model) {
        model.addAttribute("post", postService.findById(id));
        return "post-update";
    }


}
