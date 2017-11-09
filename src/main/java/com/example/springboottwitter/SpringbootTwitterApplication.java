package com.example.springboottwitter;

import com.example.springboottwitter.config.PictureUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author zhin
 */
@SpringBootApplication
@EnableConfigurationProperties({PictureUploadProperties.class})
public class SpringbootTwitterApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootTwitterApplication.class, args);
  }
}
