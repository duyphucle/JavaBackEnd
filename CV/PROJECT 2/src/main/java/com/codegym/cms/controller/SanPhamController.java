 package com.codegym.cms.controller; 
 import com.codegym.cms.model.LoaiSanPham;
import com.codegym.cms.service.LoaiSanPhamService;
import com.codegym.cms.model.NhaCungCap;
import com.codegym.cms.service.NhaCungCapService;
import com.codegym.cms.model.SanPham;
import com.codegym.cms.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class SanPhamController {

    @Autowired
    private SanPhamService sanphamService;

@Autowired
    private LoaiSanPhamService loaisanphamService;
    @ModelAttribute("loaisanphams")
    public Iterable<LoaiSanPham> loaisanphams() {
        return loaisanphamService.findAllByIsDeletedEquals(0);
    }
@Autowired
    private NhaCungCapService nhacungcapService;
    @ModelAttribute("nhacungcaps")
    public Iterable<NhaCungCap> nhacungcaps() {
        return nhacungcapService.findAllByIsDeletedEquals(0);
    }

@GetMapping("/sanphams")
    public ModelAndView listSanPhams(@RequestParam(value = "s", required = false) String s) {
        Iterable<SanPham> sanphams;
            sanphams = sanphamService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/sanpham/list");
        modelAndView.addObject("sanphams", sanphams);
        return modelAndView;
    }

    @GetMapping("/create-sanpham")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/sanpham/create");
        modelAndView.addObject("sanpham", new SanPham());
        return modelAndView;
    }
@PostMapping("/create-sanpham")
    public ModelAndView checkValidation(@Valid @ModelAttribute("sanpham") SanPham sanpham, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/sanpham/create");
            return modelAndView;
        } else {
            sanphamService.create(sanpham.getTenSanPham(),sanpham.getNhaCungCap().getId(),sanpham.getLoaiSanPham().getId(),sanpham.getMoTa(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/sanpham/create");
            modelAndView.addObject("sanpham", new SanPham());
            modelAndView.addObject("message", "New sanpham created successfully");
            return modelAndView;
        }
    }

@GetMapping("/edit-sanpham/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        SanPham sanpham = sanphamService.findById(id);
        if (sanpham != null) {
            ModelAndView modelAndView = new ModelAndView("/sanpham/edit");
            modelAndView.addObject("sanpham", sanpham);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
@PostMapping("/edit-sanpham")
    public ModelAndView updateSanPham(@ModelAttribute("sanpham") SanPham sanpham) {
        sanphamService.edit(sanpham.getTenSanPham(),sanpham.getNhaCungCap().getId(),sanpham.getLoaiSanPham().getId(),sanpham.getMoTa(), LocalDate.now(), "Dan",sanpham.getId());

        ModelAndView modelAndView = new ModelAndView("/sanpham/edit");
        modelAndView.addObject("sanpham", sanpham);
        modelAndView.addObject("message", "SanPham updated successfully");
        return modelAndView;
    }

@GetMapping("/delete-sanpham/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        sanphamService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/sanphams";
    }
@GetMapping("/view-sanpham/{id}")
    public ModelAndView viewSanPham(@PathVariable("id") Long id) {
        SanPham sanpham = sanphamService.findById(id);
        if (sanpham == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/sanpham/view");
        modelAndView.addObject("sanpham", sanpham);
        return modelAndView;}
}