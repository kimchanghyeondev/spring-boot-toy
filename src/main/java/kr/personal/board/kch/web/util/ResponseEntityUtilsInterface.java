package kr.personal.board.kch.web.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;

public interface ResponseEntityUtilsInterface {

    String responseEntityToString(HttpStatus httpStatus, String message) throws JsonProcessingException;

}
