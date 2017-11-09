package com.example.springboottwitter.search.api;

import com.example.springboottwitter.search.LightTweet;
import com.example.springboottwitter.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhin
 * @date 2017/11/7
 */
@RestController
@RequestMapping("/api/search")
public class SearchApiController {



  private SearchService searchService;
  @Autowired
  public SearchApiController(SearchService searchService) {
    this.searchService = searchService;
  }

  @RequestMapping(value = "/{searchType}", method = RequestMethod.GET)
  public List<LightTweet> search(@PathVariable String searchType,
                                 @MatrixVariable List<String> keywords) {
    return searchService.search(searchType, keywords);
  }






}
