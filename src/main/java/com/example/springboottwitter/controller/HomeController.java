package com.example.springboottwitter.controller;

import com.example.springboottwitter.profile.UserProfileSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author zhin
 * @date 2017/11/7
 */
public class HomeController {

  private UserProfileSession userProfileSession;
  @Autowired
  public HomeController(UserProfileSession userProfileSession) {
    this.userProfileSession = userProfileSession;
  }

  @RequestMapping("/")
  public String home() {
    List<String> tastes = userProfileSession.getTastes();
    if (tastes.isEmpty()) {
      return "redirect:/profile";
    }
    return "redirect:/search/mixed;keywords=" + String.join(",", tastes);
  }

}
