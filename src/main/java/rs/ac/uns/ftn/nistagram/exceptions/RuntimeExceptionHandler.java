package rs.ac.uns.ftn.nistagram.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RuntimeExceptionHandler {
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    String handleException(RuntimeException e) {
        return e.getMessage();
    }
}
