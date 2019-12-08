package kr.personal.board.kch.web;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) //테스트를 진행할 때 junit에 내장된 실행자 외에 다른 실행자를 시키는 어노테이션이다.
                            // 여기서는 springrunner라는 스프링 실행자를 사용한다 이는 스프링 부트 테스트와 junit사이에 연결자 역할을 한다.
@WebMvcTest //Spring MVC에 대한 어노테이션 선언할 경우 Controller,ControllerAdvice등을 사용할 수 있다. 단 ,Service나 Component,Repository등은 사용 할 수 없는데,
            //여기서는 Controller테스트 이기 때문에 사용한다.
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void returnHelloString() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello))  //응답 본문에대한 검사
                .andDo(print());
    }
    @Test
    public void helloDtoTest() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto").param("name",name).param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))//json응답에대한 필드별 검증가능한 메소드
                .andExpect(jsonPath("$.amount",is(amount)))
                .andDo(print());
    }
}