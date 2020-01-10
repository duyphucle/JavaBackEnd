package com.codegym.cms.formatter;

import com.codegym.cms.model.CauHoi;
import com.codegym.cms.service.CauHoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class CauHoiFormatter implements Formatter<CauHoi> {

    private CauHoiService cauhoiService;

    @Autowired
    public CauHoiFormatter(CauHoiService cauhoiService) {
        this.cauhoiService = cauhoiService;
    }

    @Override
    public CauHoi parse(String text, Locale locale) throws ParseException {
        return cauhoiService.findById(Long.parseLong(text));
    }

    @Override
    public String print(CauHoi object, Locale locale) {
        return null;  }
}
 
