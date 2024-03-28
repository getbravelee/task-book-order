package bookorder.book;

import org.springframework.web.bind.annotation.ControllerAdvice;

// TODO: ControllerAdvice 공부해보기
//  flow 파악이 어려워짐
@ControllerAdvice
public class SimpleControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> IllegalArgumentException() {
        return ResponseEntity.badRequest().build();
    }
}