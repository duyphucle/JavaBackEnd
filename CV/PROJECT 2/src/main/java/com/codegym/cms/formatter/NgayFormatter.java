package com.codegym.cms.formatter;

import com.codegym.cms.model.Ngay;
import com.codegym.cms.service.NgayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class NgayFormatter implements Formatter<Ngay> {

    private NgayService ngayService;

    @Autowired
    public NgayFormatter(NgayService ngayService) {
        this.ngayService = ngayService;
    }

    @Override
    public Ngay parse(String text, Locale locale) throws ParseException {
        return ngayService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Ngay object, Locale locale) {
        return null;  }
}
 
