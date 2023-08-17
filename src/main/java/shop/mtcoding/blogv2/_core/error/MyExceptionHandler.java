package shop.mtcoding.blogv2._core.error;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shop.mtcoding.blogv2._core.util.Script;

@RestControllerAdvice // 스로우가 터지면 전부 일로 모인다.
public class MyExceptionHandler {

  @ExceptionHandler(RuntimeException.class)
  public String error(RuntimeException e) {
    return Script.back(e.getMessage());
  }

}
