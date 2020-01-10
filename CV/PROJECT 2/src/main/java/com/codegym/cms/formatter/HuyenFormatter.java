package com.codegym.cms.formatter;

import com.codegym.cms.model.Huyen;
import com.codegym.cms.service.HuyenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class HuyenFormatter implements Formatter<Huyen> {

    private HuyenService huyenService;

    @Autowired
    public HuyenFormatter(HuyenService huyenService) {
        this.huyenService = huyenService;
    }

    @Override
    public Huyen parse(String text, Locale locale) throws ParseException {
        return huyenService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Huyen object, Locale locale) {
        return null;  }
}
 
