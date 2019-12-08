package kr.personal.board.kch.handler.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class Custom_Bad_REQUEST extends RuntimeException {


    public Custom_Bad_REQUEST(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }


}
