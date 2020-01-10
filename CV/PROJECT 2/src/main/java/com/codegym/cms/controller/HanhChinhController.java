 package com.codegym.cms.controller; 
 import com.codegym.cms.model.HanhChinh;
import com.codegym.cms.service.HanhChinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class HanhChinhController {

    @Autowired
    private HanhChinhService hanhchinhService;


@GetMapping("/hanhchinhs")
    public ModelAndView listHanhChinhs(@RequestParam(value = "s", required = false) String s) {
        Iterable<HanhChinh> hanhchinhs;
            hanhchinhs = hanhchinhService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/hanhchinh/list");
        modelAndView.addObject("hanhchinhs", hanhchinhs);
        return modelAndView;
    }

    @GetMapping("/create-hanhchinh")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/hanhchinh/create");
        modelAndView.addObject("hanhchinh", new HanhChinh());
        return modelAndView;
    }
@PostMapping("/create-hanhchinh")
    public ModelAndView checkValidation(@Valid @ModelAttribute("hanhchinh") HanhChinh hanhchinh, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/hanhchinh/create");
            return modelAndView;
        } else {
            hanhchinhService.create(hanhchinh.getTenPhuong(),hanhchinh.getCap(),hanhchinh.getMAQH(),hanhchinh.getQuanHuyen(),hanhchinh.getMaTP(),hanhchinh.getThanhPho(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/hanhchinh/create");
            modelAndView.addObject("hanhchinh", new HanhChinh());
            modelAndView.addObject("message", "New hanhchinh created successfully");
            return modelAndView;
        }
    }

@GetMapping("/edit-hanhchinh/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        HanhChinh hanhchinh = hanhchinhService.findById(id);
        if (hanhchinh != null) {
            ModelAndView modelAndView = new ModelAndView("/hanhchinh/edit");
            modelAndView.addObject("hanhchinh", hanhchinh);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
@PostMapping("/edit-hanhchinh")
    public ModelAndView updateHanhChinh(@ModelAttribute("hanhchinh") HanhChinh hanhchinh) {
        hanhchinhService.edit(hanhchinh.getTenPhuong(),hanhchinh.getCap(),hanhchinh.getMAQH(),hanhchinh.getQuanHuyen(),hanhchinh.getMaTP(),hanhchinh.getThanhPho(), LocalDate.now(), "Dan",hanhchinh.getId());

        ModelAndView modelAndView = new ModelAndView("/hanhchinh/edit");
        modelAndView.addObject("hanhchinh", hanhchinh);
        modelAndView.addObject("message", "HanhChinh updated successfully");
        return modelAndView;
    }

@GetMapping("/delete-hanhchinh/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        hanhchinhService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/hanhchinhs";
    }
@GetMapping("/view-hanhchinh/{id}")
    public ModelAndView viewHanhChinh(@PathVariable("id") Long id) {
        HanhChinh hanhchinh = hanhchinhService.findById(id);
        if (hanhchinh == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/hanhchinh/view");
        modelAndView.addObject("hanhchinh", hanhchinh);
        return modelAndView;}
}