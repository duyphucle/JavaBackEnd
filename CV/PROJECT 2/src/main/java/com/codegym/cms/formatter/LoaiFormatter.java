package com.codegym.cms.formatter;

import com.codegym.cms.model.Loai;
import com.codegym.cms.service.LoaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class LoaiFormatter implements Formatter<Loai> {

    private LoaiService loaiService;

    @Autowired
    public LoaiFormatter(LoaiService loaiService) {
        this.loaiService = loaiService;
    }

    @Override
    public Loai parse(String text, Locale locale) throws ParseException {
        return loaiService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Loai object, Locale locale) {
        return null;  }
}
 
