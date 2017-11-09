package com.example.springboottwitter.profile;

import com.example.springboottwitter.config.PictureUploadProperties;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.Locale;

/**
 * @author zhin
 * @date 2017/11/7
 */
@Controller
@SessionAttributes("picturePath")
public class PictureUploadController {

  private final Resource picturesDir;
  private final Resource anonymousPicture;
  private final MessageSource messageSource;
  private final UserProfileSession userProfileSession;

  @Autowired
  public PictureUploadController(PictureUploadProperties uploadProperties,
                                 MessageSource messageSource,
                                 UserProfileSession userProfileSession) {
    picturesDir = uploadProperties.getUploadPath();
    anonymousPicture = uploadProperties.getAnonymousPicture();
    this.messageSource = messageSource;
    this.userProfileSession = userProfileSession;
  }


  @RequestMapping(value = "/uploadedPicture")
  public void getUploadedPicture(HttpServletResponse response) throws IOException {
    Resource picturePath = userProfileSession.getPicturePath();
    if (picturePath == null) {
      picturePath = anonymousPicture;
    }
    response.setHeader("Content-Type", URLConnection
        .guessContentTypeFromName(picturePath.toString()));
    IOUtils.copy(picturePath.getInputStream(), response.getOutputStream());
  }


  @RequestMapping(value = "/profile", params = {"upload"}, method = RequestMethod.POST)
  public String onUpload(MultipartFile file, RedirectAttributes redirectAttributes,
                         Locale locale) throws IOException {

    if (file.isEmpty() || !isImage(file)) {
      redirectAttributes.addFlashAttribute("error",
          messageSource.getMessage("upload.image", null, locale));
      return "redirect:profile";
    }
    Resource picturePath = copyFileToPictures(file);
    userProfileSession.setPicturePath(picturePath);

    if (picturePath.exists()) {
      redirectAttributes.addAttribute("uploadSuccess",
          messageSource.getMessage("upload.image.success", null, locale));
    }

    return "redirect:profile";
  }


  private Resource copyFileToPictures(MultipartFile file) throws IOException {
    String fileExtension = getFileExtension(file.getOriginalFilename());

    File tempFile = File.createTempFile("pic", fileExtension, picturesDir.getFile());
    try (InputStream in = file.getInputStream();
         OutputStream out = new FileOutputStream(tempFile)) {
      IOUtils.copy(in, out);
    }
    return new FileSystemResource(tempFile);
  }


  @RequestMapping("uploadError")
  public ModelAndView onUploadError(Locale locale) {
    ModelAndView modelAndView = new ModelAndView("profile/profilePage");
    modelAndView.addObject("error",
        messageSource.getMessage("upload.file.too.big", null, locale));
    modelAndView.addObject("profileForm", userProfileSession.toForm());
    return modelAndView;
  }

  @ExceptionHandler
  public ModelAndView handleIOException(Locale locale) {
    ModelAndView modelAndView = new ModelAndView("profile/profilePage");
    modelAndView.addObject("error",
        messageSource.getMessage("upload.io.exception", null, locale));
    modelAndView.addObject("profileForm", userProfileSession.toForm());
    return modelAndView;
  }

  @ModelAttribute("picturePath")
  public Resource picturePath() {
    return anonymousPicture;
  }


  private static String getFileExtension(String name) {
    return name.substring(name.lastIndexOf("."));
  }

  private boolean isImage(MultipartFile file) {
    return file.getContentType().startsWith("image");
  }

}
