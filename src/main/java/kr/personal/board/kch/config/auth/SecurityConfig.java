package kr.personal.board.kch.config.auth;

import kr.personal.board.kch.web.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //springSecurity 기능 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOauth2UserService customOauth2UserService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()//h2-console 화면사용을 위해 해당 옵션들을 disable
                .and()
                    .authorizeRequests() //URL별 권한 설정하는 옵션의 시작점
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**")
                        .permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()//설정한 url 외에는 모두 인증되어야 한다 (login)
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login() //oatuth2로그인 기능설정에대한 진입점
                        .userInfoEndpoint() // 로그인 성공한 후 사용자 정보를 가져올때의 설정을 담당
                            .userService(customOauth2UserService); //소셜로그인 성공 시 후속 조치를 진행할 userservice인터페이스의 구현체를 등록

    }
}
