package kr.personal.board.kch.web.dto;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloReponseDtoTest {

    @Test
    public void lombokTest() {
        //given
        String name = "test";
        int amount = 1000;
        //when
        HelloReponseDto dto = new HelloReponseDto(name,amount);
        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }

}