package com.codegym.cms.formatter;

import com.codegym.cms.model.Phuong;
import com.codegym.cms.service.PhuongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class PhuongFormatter implements Formatter<Phuong> {

    private PhuongService phuongService;

    @Autowired
    public PhuongFormatter(PhuongService phuongService) {
        this.phuongService = phuongService;
    }

    @Override
    public Phuong parse(String text, Locale locale) throws ParseException {
        return phuongService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Phuong object, Locale locale) {
        return null;  }
}
 
