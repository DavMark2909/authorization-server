package authorization.exception;

import org.springframework.http.HttpStatus;

public class MyException extends RuntimeException{

    public MyException(String msg) {
        super(msg);
    }

    public HttpStatus getHttpStatus(){
        return HttpStatus.BAD_REQUEST;
    }
}
