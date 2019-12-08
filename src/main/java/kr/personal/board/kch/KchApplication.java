package kr.personal.board.kch;

import kr.personal.board.kch.web.domain.posts.Posts;
import kr.personal.board.kch.web.domain.posts.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // JPA Auditing활성화
public class KchApplication implements CommandLineRunner {


    @Autowired
    PostsRepository postsRepository;

    public static void main(String[] args) {

        SpringApplication.run(KchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            postsRepository.save(Posts.builder()
                    .title("title"+i)
                    .content("content"+i)
                    .author("kimchanghyeon"+i)
                    .build());
        }


    }
}
