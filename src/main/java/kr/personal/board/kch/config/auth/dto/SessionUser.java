package kr.personal.board.kch.config.auth.dto;

import kr.personal.board.kch.web.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.picture = user.getPicture();
    }
}
