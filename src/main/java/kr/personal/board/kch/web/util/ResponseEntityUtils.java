package kr.personal.board.kch.web.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseEntityUtils implements ResponseEntityUtilsInterface{

    private static final ObjectMapper objectMapper = new ObjectMapper();
    public String responseEntityToString(HttpStatus httpStatus,String message) throws JsonProcessingException {

        return objectMapper.writeValueAsString(ResponseEntity.status(httpStatus).body(message));

    }
}
