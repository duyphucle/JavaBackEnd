package com.codegym.cms.formatter;

import com.codegym.cms.model.DapAn;
import com.codegym.cms.service.DapAnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class DapAnFormatter implements Formatter<DapAn> {

    private DapAnService dapanService;

    @Autowired
    public DapAnFormatter(DapAnService dapanService) {
        this.dapanService = dapanService;
    }

    @Override
    public DapAn parse(String text, Locale locale) throws ParseException {
        return dapanService.findById(Long.parseLong(text));
    }

    @Override
    public String print(DapAn object, Locale locale) {
        return null;  }
}
 
