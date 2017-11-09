package com.example.springboottwitter.profile;

import com.example.springboottwitter.date.PastLocalDate;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhin
 * @date 2017/11/7
 */
public class ProfileForm {
  @Size(min = 2)
  private String twitterHandle;

  @Email
  @NotEmpty
  private String email;

  @NotNull
  @PastLocalDate
  private LocalDate birthDate;

  @NotEmpty
  private List<String> tastes = new ArrayList<>();

  @Override
  public String toString() {
    return "ProfileForm{" +
        "twitterHandle='" + twitterHandle + '\'' +
        ", email='" + email + '\'' +
        ", birthDate=" + birthDate +
        ", tastes=" + tastes +
        '}';
  }

  public String getTwitterHandle() {
    return twitterHandle;
  }

  public void setTwitterHandle(String twitterHandle) {
    this.twitterHandle = twitterHandle;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public List<String> getTastes() {
    return tastes;
  }

  public void setTastes(List<String> tastes) {
    this.tastes = tastes;
  }
}
