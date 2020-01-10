package com.codegym.cms.formatter;

import com.codegym.cms.model.Sell;
import com.codegym.cms.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class SellFormatter implements Formatter<Sell> {

    private SellService sellService;

    @Autowired
    public SellFormatter(SellService sellService) {
        this.sellService = sellService;
    }

    @Override
    public Sell parse(String text, Locale locale) throws ParseException {
        return sellService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Sell object, Locale locale) {
        return null;  }
}
 
