 package com.codegym.cms.controller; 
 import com.codegym.cms.model.SanPham;
import com.codegym.cms.service.SanPhamService;
import com.codegym.cms.model.NhapKho;
import com.codegym.cms.service.NhapKhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class NhapKhoController {

    @Autowired
    private NhapKhoService nhapkhoService;

@Autowired
    private SanPhamService sanphamService;
    @ModelAttribute("sanphams")
    public Iterable<SanPham> sanphams() {
        return sanphamService.findAllByIsDeletedEquals(0);
    }

@GetMapping("/nhapkhos")
    public ModelAndView listNhapKhos(@RequestParam(value = "s", required = false) String s) {
        Iterable<NhapKho> nhapkhos;
            nhapkhos = nhapkhoService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/nhapkho/list");
        modelAndView.addObject("nhapkhos", nhapkhos);
        return modelAndView;
    }

    @GetMapping("/create-nhapkho")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/nhapkho/create");
        modelAndView.addObject("nhapkho", new NhapKho());
        return modelAndView;
    }
@PostMapping("/create-nhapkho")
    public ModelAndView checkValidation(@Valid @ModelAttribute("nhapkho") NhapKho nhapkho, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/nhapkho/create");
            return modelAndView;
        } else {
            nhapkhoService.create(nhapkho.getSanPham().getId(),nhapkho.getGia(),nhapkho.getNgayNhapKho(),nhapkho.getSoLuong(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/nhapkho/create");
            modelAndView.addObject("nhapkho", new NhapKho());
            modelAndView.addObject("message", "New nhapkho created successfully");
            return modelAndView;
        }
    }

@GetMapping("/edit-nhapkho/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        NhapKho nhapkho = nhapkhoService.findById(id);
        if (nhapkho != null) {
            ModelAndView modelAndView = new ModelAndView("/nhapkho/edit");
            modelAndView.addObject("nhapkho", nhapkho);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
@PostMapping("/edit-nhapkho")
    public ModelAndView updateNhapKho(@ModelAttribute("nhapkho") NhapKho nhapkho) {
        nhapkhoService.edit(nhapkho.getSanPham().getId(),nhapkho.getGia(),nhapkho.getNgayNhapKho(),nhapkho.getSoLuong(), LocalDate.now(), "Dan",nhapkho.getId());

        ModelAndView modelAndView = new ModelAndView("/nhapkho/edit");
        modelAndView.addObject("nhapkho", nhapkho);
        modelAndView.addObject("message", "NhapKho updated successfully");
        return modelAndView;
    }

@GetMapping("/delete-nhapkho/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        nhapkhoService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/nhapkhos";
    }
@GetMapping("/view-nhapkho/{id}")
    public ModelAndView viewNhapKho(@PathVariable("id") Long id) {
        NhapKho nhapkho = nhapkhoService.findById(id);
        if (nhapkho == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/nhapkho/view");
        modelAndView.addObject("nhapkho", nhapkho);
        return modelAndView;}
}