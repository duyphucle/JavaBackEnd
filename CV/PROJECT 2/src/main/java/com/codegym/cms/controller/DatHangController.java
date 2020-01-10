 package com.codegym.cms.controller; 
 import com.codegym.cms.model.User;
import com.codegym.cms.service.UserService;
import com.codegym.cms.model.DatHang;
import com.codegym.cms.service.DatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class DatHangController {

    @Autowired
    private DatHangService dathangService;

@Autowired
    private UserService userService;
    @ModelAttribute("users")
    public Iterable<User> users() {
        return userService.findAllByIsDeletedEquals(0);
    }

@GetMapping("/dathangs")
    public ModelAndView listDatHangs(@RequestParam(value = "s", required = false) String s) {
        Iterable<DatHang> dathangs;
            dathangs = dathangService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/dathang/list");
        modelAndView.addObject("dathangs", dathangs);
        return modelAndView;
    }

    @GetMapping("/create-dathang")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/dathang/create");
        modelAndView.addObject("dathang", new DatHang());
        return modelAndView;
    }
@PostMapping("/create-dathang")
    public ModelAndView checkValidation(@Valid @ModelAttribute("dathang") DatHang dathang, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/dathang/create");
            return modelAndView;
        } else {
            dathangService.create(dathang.getUser().getId(),dathang.getOrderDate(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/dathang/create");
            modelAndView.addObject("dathang", new DatHang());
            modelAndView.addObject("message", "New dathang created successfully");
            return modelAndView;
        }
    }

@GetMapping("/edit-dathang/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        DatHang dathang = dathangService.findById(id);
        if (dathang != null) {
            ModelAndView modelAndView = new ModelAndView("/dathang/edit");
            modelAndView.addObject("dathang", dathang);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
@PostMapping("/edit-dathang")
    public ModelAndView updateDatHang(@ModelAttribute("dathang") DatHang dathang) {
        dathangService.edit(dathang.getUser().getId(),dathang.getOrderDate(), LocalDate.now(), "Dan",dathang.getId());

        ModelAndView modelAndView = new ModelAndView("/dathang/edit");
        modelAndView.addObject("dathang", dathang);
        modelAndView.addObject("message", "DatHang updated successfully");
        return modelAndView;
    }

@GetMapping("/delete-dathang/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        dathangService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/dathangs";
    }
@GetMapping("/view-dathang/{id}")
    public ModelAndView viewDatHang(@PathVariable("id") Long id) {
        DatHang dathang = dathangService.findById(id);
        if (dathang == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/dathang/view");
        modelAndView.addObject("dathang", dathang);
        return modelAndView;}
}