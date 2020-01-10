 package com.codegym.cms.controller; 
 import com.codegym.cms.model.SanPham;
import com.codegym.cms.service.SanPhamService;
import com.codegym.cms.model.Sell;
import com.codegym.cms.service.SellService;
import com.codegym.cms.model.SellTheoSanPham;
import com.codegym.cms.service.SellTheoSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class SellTheoSanPhamController {

    @Autowired
    private SellTheoSanPhamService selltheosanphamService;

@Autowired
    private SanPhamService sanphamService;
    @ModelAttribute("sanphams")
    public Iterable<SanPham> sanphams() {
        return sanphamService.findAllByIsDeletedEquals(0);
    }
@Autowired
    private SellService sellService;
    @ModelAttribute("sells")
    public Iterable<Sell> sells() {
        return sellService.findAllByIsDeletedEquals(0);
    }

@GetMapping("/selltheosanphams")
    public ModelAndView listSellTheoSanPhams(@RequestParam(value = "s", required = false) String s) {
        Iterable<SellTheoSanPham> selltheosanphams;
            selltheosanphams = selltheosanphamService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/selltheosanpham/list");
        modelAndView.addObject("selltheosanphams", selltheosanphams);
        return modelAndView;
    }

    @GetMapping("/create-selltheosanpham")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/selltheosanpham/create");
        modelAndView.addObject("selltheosanpham", new SellTheoSanPham());
        return modelAndView;
    }
@PostMapping("/create-selltheosanpham")
    public ModelAndView checkValidation(@Valid @ModelAttribute("selltheosanpham") SellTheoSanPham selltheosanpham, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/selltheosanpham/create");
            return modelAndView;
        } else {
            selltheosanphamService.create(selltheosanpham.getSell().getId(),selltheosanpham.getSanPham().getId(),selltheosanpham.getGia(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/selltheosanpham/create");
            modelAndView.addObject("selltheosanpham", new SellTheoSanPham());
            modelAndView.addObject("message", "New selltheosanpham created successfully");
            return modelAndView;
        }
    }

@GetMapping("/edit-selltheosanpham/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        SellTheoSanPham selltheosanpham = selltheosanphamService.findById(id);
        if (selltheosanpham != null) {
            ModelAndView modelAndView = new ModelAndView("/selltheosanpham/edit");
            modelAndView.addObject("selltheosanpham", selltheosanpham);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
@PostMapping("/edit-selltheosanpham")
    public ModelAndView updateSellTheoSanPham(@ModelAttribute("selltheosanpham") SellTheoSanPham selltheosanpham) {
        selltheosanphamService.edit(selltheosanpham.getSell().getId(),selltheosanpham.getSanPham().getId(),selltheosanpham.getGia(), LocalDate.now(), "Dan",selltheosanpham.getId());

        ModelAndView modelAndView = new ModelAndView("/selltheosanpham/edit");
        modelAndView.addObject("selltheosanpham", selltheosanpham);
        modelAndView.addObject("message", "SellTheoSanPham updated successfully");
        return modelAndView;
    }

@GetMapping("/delete-selltheosanpham/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        selltheosanphamService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/selltheosanphams";
    }
@GetMapping("/view-selltheosanpham/{id}")
    public ModelAndView viewSellTheoSanPham(@PathVariable("id") Long id) {
        SellTheoSanPham selltheosanpham = selltheosanphamService.findById(id);
        if (selltheosanpham == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/selltheosanpham/view");
        modelAndView.addObject("selltheosanpham", selltheosanpham);
        return modelAndView;}
}