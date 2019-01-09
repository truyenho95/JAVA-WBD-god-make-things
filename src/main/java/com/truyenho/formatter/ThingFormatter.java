package com.truyenho.formatter;

import com.truyenho.model.Thing;
import com.truyenho.service.ThingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class ThingFormatter implements Formatter<Thing> {

  private ThingService thingService;

  @Autowired
  public ThingFormatter(ThingService thingService) {
    this.thingService = thingService;
  }

  @Override
  public Thing parse(String text, Locale locale) throws ParseException {
    return thingService.findById(Long.parseLong(text));
  }

  @Override
  public String print(Thing object, Locale locale) {
    return "[" + object.getName() + "]";
  }
}
