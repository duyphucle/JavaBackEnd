package com.codegym.cms.formatter;

import com.codegym.cms.model.SanPham;
import com.codegym.cms.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class SanPhamFormatter implements Formatter<SanPham> {

    private SanPhamService sanphamService;

    @Autowired
    public SanPhamFormatter(SanPhamService sanphamService) {
        this.sanphamService = sanphamService;
    }

    @Override
    public SanPham parse(String text, Locale locale) throws ParseException {
        return sanphamService.findById(Long.parseLong(text));
    }

    @Override
    public String print(SanPham object, Locale locale) {
        return null;  }
}
 
