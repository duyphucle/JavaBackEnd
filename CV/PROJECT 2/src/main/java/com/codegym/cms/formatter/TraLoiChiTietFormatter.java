package com.codegym.cms.formatter;

import com.codegym.cms.model.TraLoiChiTiet;
import com.codegym.cms.service.TraLoiChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class TraLoiChiTietFormatter implements Formatter<TraLoiChiTiet> {

    private TraLoiChiTietService traloichitietService;

    @Autowired
    public TraLoiChiTietFormatter(TraLoiChiTietService traloichitietService) {
        this.traloichitietService = traloichitietService;
    }

    @Override
    public TraLoiChiTiet parse(String text, Locale locale) throws ParseException {
        return traloichitietService.findById(Long.parseLong(text));
    }

    @Override
    public String print(TraLoiChiTiet object, Locale locale) {
        return null;  }
}
 
