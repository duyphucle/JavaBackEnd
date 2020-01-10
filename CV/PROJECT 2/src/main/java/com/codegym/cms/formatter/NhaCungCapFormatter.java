package com.codegym.cms.formatter;

import com.codegym.cms.model.NhaCungCap;
import com.codegym.cms.service.NhaCungCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class NhaCungCapFormatter implements Formatter<NhaCungCap> {

    private NhaCungCapService nhacungcapService;

    @Autowired
    public NhaCungCapFormatter(NhaCungCapService nhacungcapService) {
        this.nhacungcapService = nhacungcapService;
    }

    @Override
    public NhaCungCap parse(String text, Locale locale) throws ParseException {
        return nhacungcapService.findById(Long.parseLong(text));
    }

    @Override
    public String print(NhaCungCap object, Locale locale) {
        return null;  }
}
 
