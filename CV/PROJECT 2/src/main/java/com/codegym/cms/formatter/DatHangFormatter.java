package com.codegym.cms.formatter;

import com.codegym.cms.model.DatHang;
import com.codegym.cms.service.DatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class DatHangFormatter implements Formatter<DatHang> {

    private DatHangService dathangService;

    @Autowired
    public DatHangFormatter(DatHangService dathangService) {
        this.dathangService = dathangService;
    }

    @Override
    public DatHang parse(String text, Locale locale) throws ParseException {
        return dathangService.findById(Long.parseLong(text));
    }

    @Override
    public String print(DatHang object, Locale locale) {
        return null;  }
}
 
