package com.codegym.cms.formatter;

import com.codegym.cms.model.Thang;
import com.codegym.cms.service.ThangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class ThangFormatter implements Formatter<Thang> {

    private ThangService thangService;

    @Autowired
    public ThangFormatter(ThangService thangService) {
        this.thangService = thangService;
    }

    @Override
    public Thang parse(String text, Locale locale) throws ParseException {
        return thangService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Thang object, Locale locale) {
        return null;  }
}
 
