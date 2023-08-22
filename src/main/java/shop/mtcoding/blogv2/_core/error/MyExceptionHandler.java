package shop.mtcoding.blogv2._core.error;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shop.mtcoding.blogv2._core.error.ex.MyApiException;
import shop.mtcoding.blogv2._core.util.ApiUtil;
import shop.mtcoding.blogv2._core.util.Script;


@RestControllerAdvice // 스로우가 터지면 전부 일로 모인다. 내부적으로 responseBody가 붙어 있음
public class MyExceptionHandler {

  @ExceptionHandler(RuntimeException.class)
  public String error(RuntimeException e) {
    return Script.back(e.getMessage());
  }

  @ExceptionHandler(MyApiException.class)
  public ApiUtil<String> error(MyApiException e) {
    return new ApiUtil<String>(false, e.getMessage());
  }


}
