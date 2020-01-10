package com.codegym.cms.formatter;

import com.codegym.cms.model.HanhChinh;
import com.codegym.cms.service.HanhChinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class HanhChinhFormatter implements Formatter<HanhChinh> {

    private HanhChinhService hanhchinhService;

    @Autowired
    public HanhChinhFormatter(HanhChinhService hanhchinhService) {
        this.hanhchinhService = hanhchinhService;
    }

    @Override
    public HanhChinh parse(String text, Locale locale) throws ParseException {
        return hanhchinhService.findById(Long.parseLong(text));
    }

    @Override
    public String print(HanhChinh object, Locale locale) {
        return null;  }
}
 
