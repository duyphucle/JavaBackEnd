package com.codegym.cms.formatter;

import com.codegym.cms.model.Xe;
import com.codegym.cms.service.XeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class XeFormatter implements Formatter<Xe> {

    private XeService xeService;

    @Autowired
    public XeFormatter(XeService xeService) {
        this.xeService = xeService;
    }

    @Override
    public Xe parse(String text, Locale locale) throws ParseException {
        return xeService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Xe object, Locale locale) {
        return null;  }
}
 
