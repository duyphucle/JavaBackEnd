package com.codegym.cms.formatter;

import com.codegym.cms.model.NhapKho;
import com.codegym.cms.service.NhapKhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class NhapKhoFormatter implements Formatter<NhapKho> {

    private NhapKhoService nhapkhoService;

    @Autowired
    public NhapKhoFormatter(NhapKhoService nhapkhoService) {
        this.nhapkhoService = nhapkhoService;
    }

    @Override
    public NhapKho parse(String text, Locale locale) throws ParseException {
        return nhapkhoService.findById(Long.parseLong(text));
    }

    @Override
    public String print(NhapKho object, Locale locale) {
        return null;  }
}
 
