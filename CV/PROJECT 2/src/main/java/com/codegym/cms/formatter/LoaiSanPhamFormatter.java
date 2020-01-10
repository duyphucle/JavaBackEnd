package com.codegym.cms.formatter;

import com.codegym.cms.model.LoaiSanPham;
import com.codegym.cms.service.LoaiSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class LoaiSanPhamFormatter implements Formatter<LoaiSanPham> {

    private LoaiSanPhamService loaisanphamService;

    @Autowired
    public LoaiSanPhamFormatter(LoaiSanPhamService loaisanphamService) {
        this.loaisanphamService = loaisanphamService;
    }

    @Override
    public LoaiSanPham parse(String text, Locale locale) throws ParseException {
        return loaisanphamService.findById(Long.parseLong(text));
    }

    @Override
    public String print(LoaiSanPham object, Locale locale) {
        return null;  }
}
 
