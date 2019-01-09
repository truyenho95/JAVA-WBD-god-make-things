package com.truyenho.controller;

import com.truyenho.service.ThingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ThingController {

  @Autowired
  private ThingService thingService;

  @GetMapping("/")
  public ModelAndView redirectIndex() {
    return new ModelAndView("redirect:/thing");
  }

  @GetMapping("/thing")
  public ModelAndView index() {
    return new ModelAndView("thing/index");
  }
}
