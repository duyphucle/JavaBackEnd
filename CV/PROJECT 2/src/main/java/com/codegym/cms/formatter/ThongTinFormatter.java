package com.codegym.cms.formatter;

import com.codegym.cms.model.ThongTin;
import com.codegym.cms.service.ThongTinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class ThongTinFormatter implements Formatter<ThongTin> {

    private ThongTinService thongtinService;

    @Autowired
    public ThongTinFormatter(ThongTinService thongtinService) {
        this.thongtinService = thongtinService;
    }

    @Override
    public ThongTin parse(String text, Locale locale) throws ParseException {
        return thongtinService.findById(Long.parseLong(text));
    }

    @Override
    public String print(ThongTin object, Locale locale) {
        return null;  }
}
 
