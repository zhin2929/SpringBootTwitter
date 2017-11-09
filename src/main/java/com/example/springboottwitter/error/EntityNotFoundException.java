package com.example.springboottwitter.error;

/**
 * @author zhin
 * @date 2017/11/7
 */
public class EntityNotFoundException extends Exception {

  public EntityNotFoundException(String message) {
    super(message);
  }
  public EntityNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

}
