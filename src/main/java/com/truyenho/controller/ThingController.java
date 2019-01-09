package com.truyenho.controller;

import com.truyenho.model.Thing;
import com.truyenho.service.ThingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

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
    ModelAndView modelAndView = new ModelAndView("thing/index");
    modelAndView.addObject("thing", new Thing());
    return modelAndView;
  }

  @PostMapping("/create-thing")
  public ModelAndView saveThing(@ModelAttribute("thing") Thing thing) {
    thingService.save(thing);

    ModelAndView modelAndView = new ModelAndView("redirect:/thing/{id}");
    modelAndView.addObject("id", thing.getId());
    return modelAndView;
  }

  @GetMapping("/thing/{id}")
  public ModelAndView viewDetail(@PathVariable Long id) {
    Thing thing = thingService.findById(id);
    if (thing != null) {
      ModelAndView modelAndView = new ModelAndView("/thing/detail");
      modelAndView.addObject("thing", thing);
      return modelAndView;

    } else {
      ModelAndView modelAndView = new ModelAndView("/error.404");
      return modelAndView;
    }
  }

  @GetMapping("/thing/s")
  public ModelAndView listThings(@RequestParam("name") Optional<String> name, Pageable pageable){
    Page<Thing> things;
    if (name.isPresent()) {
      things = thingService.findAllByNameContaining(name.get(), pageable);
    } else {
      things = thingService.findAll(new PageRequest(pageable.getPageNumber(), 10));
    }
    ModelAndView modelAndView = new ModelAndView("thing/list");
    modelAndView.addObject("things", things);
    return modelAndView;
  }
}
