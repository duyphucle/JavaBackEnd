 package com.codegym.cms.controller; 
 import com.codegym.cms.model.LoaiSanPham;
import com.codegym.cms.service.LoaiSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class LoaiSanPhamController {

    @Autowired
    private LoaiSanPhamService loaisanphamService;


@GetMapping("/loaisanphams")
    public ModelAndView listLoaiSanPhams(@RequestParam(value = "s", required = false) String s) {
        Iterable<LoaiSanPham> loaisanphams;
            loaisanphams = loaisanphamService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/loaisanpham/list");
        modelAndView.addObject("loaisanphams", loaisanphams);
        return modelAndView;
    }

    @GetMapping("/create-loaisanpham")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/loaisanpham/create");
        modelAndView.addObject("loaisanpham", new LoaiSanPham());
        return modelAndView;
    }
@PostMapping("/create-loaisanpham")
    public ModelAndView checkValidation(@Valid @ModelAttribute("loaisanpham") LoaiSanPham loaisanpham, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/loaisanpham/create");
            return modelAndView;
        } else {
            loaisanphamService.create(loaisanpham.getTenTheLoai(),loaisanpham.getMoTa(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/loaisanpham/create");
            modelAndView.addObject("loaisanpham", new LoaiSanPham());
            modelAndView.addObject("message", "New loaisanpham created successfully");
            return modelAndView;
        }
    }

@GetMapping("/edit-loaisanpham/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        LoaiSanPham loaisanpham = loaisanphamService.findById(id);
        if (loaisanpham != null) {
            ModelAndView modelAndView = new ModelAndView("/loaisanpham/edit");
            modelAndView.addObject("loaisanpham", loaisanpham);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
@PostMapping("/edit-loaisanpham")
    public ModelAndView updateLoaiSanPham(@ModelAttribute("loaisanpham") LoaiSanPham loaisanpham) {
        loaisanphamService.edit(loaisanpham.getTenTheLoai(),loaisanpham.getMoTa(), LocalDate.now(), "Dan",loaisanpham.getId());

        ModelAndView modelAndView = new ModelAndView("/loaisanpham/edit");
        modelAndView.addObject("loaisanpham", loaisanpham);
        modelAndView.addObject("message", "LoaiSanPham updated successfully");
        return modelAndView;
    }

@GetMapping("/delete-loaisanpham/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        loaisanphamService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/loaisanphams";
    }
@GetMapping("/view-loaisanpham/{id}")
    public ModelAndView viewLoaiSanPham(@PathVariable("id") Long id) {
        LoaiSanPham loaisanpham = loaisanphamService.findById(id);
        if (loaisanpham == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/loaisanpham/view");
        modelAndView.addObject("loaisanpham", loaisanpham);
        return modelAndView;}
}