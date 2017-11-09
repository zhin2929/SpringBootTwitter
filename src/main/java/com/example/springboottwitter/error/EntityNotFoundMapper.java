package com.example.springboottwitter.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author zhin
 * @date 2017/11/7
 */
@ControllerAdvice
public class EntityNotFoundMapper {

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entitycould not be found")
  public void handleNotFound() {

  }


}
