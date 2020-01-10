package com.codegym.cms.formatter;

import com.codegym.cms.model.ThanhPho;
import com.codegym.cms.service.ThanhPhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class ThanhPhoFormatter implements Formatter<ThanhPho> {

    private ThanhPhoService thanhphoService;

    @Autowired
    public ThanhPhoFormatter(ThanhPhoService thanhphoService) {
        this.thanhphoService = thanhphoService;
    }

    @Override
    public ThanhPho parse(String text, Locale locale) throws ParseException {
        return thanhphoService.findById(Long.parseLong(text));
    }

    @Override
    public String print(ThanhPho object, Locale locale) {
        return null;  }
}
 
