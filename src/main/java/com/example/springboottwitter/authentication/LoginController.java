package com.example.springboottwitter.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhin
 * @date 2017/11/8
 */
@Controller
public class LoginController {

  @RequestMapping("/login")
  public String authenticate() {
    return "login";
  }



}
