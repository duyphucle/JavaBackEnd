package com.codegym.cms.formatter;

import com.codegym.cms.model.TraLoi;
import com.codegym.cms.service.TraLoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class TraLoiFormatter implements Formatter<TraLoi> {

    private TraLoiService traloiService;

    @Autowired
    public TraLoiFormatter(TraLoiService traloiService) {
        this.traloiService = traloiService;
    }

    @Override
    public TraLoi parse(String text, Locale locale) throws ParseException {
        return traloiService.findById(Long.parseLong(text));
    }

    @Override
    public String print(TraLoi object, Locale locale) {
        return null;  }
}
 
