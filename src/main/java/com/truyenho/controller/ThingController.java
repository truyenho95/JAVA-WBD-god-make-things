package com.truyenho.controller;

import com.truyenho.model.Thing;
import com.truyenho.service.ThingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

  /*@GetMapping("/thing/s")
  public ModelAndView listThings(@RequestParam("s") Optional<String> s, Pageable pageable){
    Iterable things = thingService.findAll();
    ModelAndView modelAndView = new ModelAndView("/thing/list");
    modelAndView.addObject("things", things);
    return modelAndView;
  }*/
}
