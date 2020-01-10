package com.codegym.cms.formatter;

import com.codegym.cms.model.Nam;
import com.codegym.cms.service.NamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class NamFormatter implements Formatter<Nam> {

    private NamService namService;

    @Autowired
    public NamFormatter(NamService namService) {
        this.namService = namService;
    }

    @Override
    public Nam parse(String text, Locale locale) throws ParseException {
        return namService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Nam object, Locale locale) {
        return null;  }
}
 
