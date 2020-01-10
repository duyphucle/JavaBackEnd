package com.codegym.cms.formatter;

import com.codegym.cms.model.DatHangChiTiet;
import com.codegym.cms.service.DatHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class DatHangChiTietFormatter implements Formatter<DatHangChiTiet> {

    private DatHangChiTietService dathangchitietService;

    @Autowired
    public DatHangChiTietFormatter(DatHangChiTietService dathangchitietService) {
        this.dathangchitietService = dathangchitietService;
    }

    @Override
    public DatHangChiTiet parse(String text, Locale locale) throws ParseException {
        return dathangchitietService.findById(Long.parseLong(text));
    }

    @Override
    public String print(DatHangChiTiet object, Locale locale) {
        return null;  }
}
 
