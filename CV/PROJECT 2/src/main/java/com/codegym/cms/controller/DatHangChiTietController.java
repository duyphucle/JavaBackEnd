 package com.codegym.cms.controller; 
 import com.codegym.cms.model.DatHang;
import com.codegym.cms.service.DatHangService;
import com.codegym.cms.model.SellTheoSanPham;
import com.codegym.cms.service.SellTheoSanPhamService;
import com.codegym.cms.model.DatHangChiTiet;
import com.codegym.cms.service.DatHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class DatHangChiTietController {

    @Autowired
    private DatHangChiTietService dathangchitietService;

@Autowired
    private DatHangService dathangService;
    @ModelAttribute("dathangs")
    public Iterable<DatHang> dathangs() {
        return dathangService.findAllByIsDeletedEquals(0);
    }
@Autowired
    private SellTheoSanPhamService selltheosanphamService;
    @ModelAttribute("selltheosanphams")
    public Iterable<SellTheoSanPham> selltheosanphams() {
        return selltheosanphamService.findAllByIsDeletedEquals(0);
    }

@GetMapping("/dathangchitiets")
    public ModelAndView listDatHangChiTiets(@RequestParam(value = "s", required = false) String s) {
        Iterable<DatHangChiTiet> dathangchitiets;
            dathangchitiets = dathangchitietService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/dathangchitiet/list");
        modelAndView.addObject("dathangchitiets", dathangchitiets);
        return modelAndView;
    }

    @GetMapping("/create-dathangchitiet")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/dathangchitiet/create");
        modelAndView.addObject("dathangchitiet", new DatHangChiTiet());
        return modelAndView;
    }
@PostMapping("/create-dathangchitiet")
    public ModelAndView checkValidation(@Valid @ModelAttribute("dathangchitiet") DatHangChiTiet dathangchitiet, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/dathangchitiet/create");
            return modelAndView;
        } else {
            dathangchitietService.create(dathangchitiet.getDatHang().getId(),dathangchitiet.getSellTheoSanPham().getId(),dathangchitiet.getSoLuong(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/dathangchitiet/create");
            modelAndView.addObject("dathangchitiet", new DatHangChiTiet());
            modelAndView.addObject("message", "New dathangchitiet created successfully");
            return modelAndView;
        }
    }

@GetMapping("/edit-dathangchitiet/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        DatHangChiTiet dathangchitiet = dathangchitietService.findById(id);
        if (dathangchitiet != null) {
            ModelAndView modelAndView = new ModelAndView("/dathangchitiet/edit");
            modelAndView.addObject("dathangchitiet", dathangchitiet);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
@PostMapping("/edit-dathangchitiet")
    public ModelAndView updateDatHangChiTiet(@ModelAttribute("dathangchitiet") DatHangChiTiet dathangchitiet) {
        dathangchitietService.edit(dathangchitiet.getDatHang().getId(),dathangchitiet.getSellTheoSanPham().getId(),dathangchitiet.getSoLuong(), LocalDate.now(), "Dan",dathangchitiet.getId());

        ModelAndView modelAndView = new ModelAndView("/dathangchitiet/edit");
        modelAndView.addObject("dathangchitiet", dathangchitiet);
        modelAndView.addObject("message", "DatHangChiTiet updated successfully");
        return modelAndView;
    }

@GetMapping("/delete-dathangchitiet/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        dathangchitietService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/dathangchitiets";
    }
@GetMapping("/view-dathangchitiet/{id}")
    public ModelAndView viewDatHangChiTiet(@PathVariable("id") Long id) {
        DatHangChiTiet dathangchitiet = dathangchitietService.findById(id);
        if (dathangchitiet == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/dathangchitiet/view");
        modelAndView.addObject("dathangchitiet", dathangchitiet);
        return modelAndView;}
}