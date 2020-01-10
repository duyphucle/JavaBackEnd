package com.codegym.cms.formatter;

import com.codegym.cms.model.SellTheoSanPham;
import com.codegym.cms.service.SellTheoSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class SellTheoSanPhamFormatter implements Formatter<SellTheoSanPham> {

    private SellTheoSanPhamService selltheosanphamService;

    @Autowired
    public SellTheoSanPhamFormatter(SellTheoSanPhamService selltheosanphamService) {
        this.selltheosanphamService = selltheosanphamService;
    }

    @Override
    public SellTheoSanPham parse(String text, Locale locale) throws ParseException {
        return selltheosanphamService.findById(Long.parseLong(text));
    }

    @Override
    public String print(SellTheoSanPham object, Locale locale) {
        return null;  }
}
 
