package kr.personal.board.kch.config.auth;

import kr.personal.board.kch.web.domain.user.Role;
import kr.personal.board.kch.web.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.Objects;

@Getter
public class OAuthAttributes {
    private Map<String,Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;


    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    //Oatuh2User에서 반환하는 사용자 정보는 Map이기 때문에 값을 하나하나 변환해야 한다
    public static OAuthAttributes of(String registrationId,String userNameAttributeName,Map<String,Object> attributes){
        return ofGoogle(userNameAttributeName,attributes);
    }
    public static OAuthAttributes ofGoogle(String userNameAttributeName,Map<String,Object> attributes){
        return OAuthAttributes.builder()
                .name(Objects.toString(attributes.get("name")))
                .email(Objects.toString(attributes.get("email")))
                .picture(Objects.toString(attributes.get("picture")))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    //userEntity생성, 생성의 시점은 처음 가입할때 , 처음 가입할때의 권한은 게스트
    public User toEntity(){

        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }

}
