 package com.codegym.cms.controller; 
 import com.codegym.cms.model.ThongTin;
import com.codegym.cms.service.ThongTinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class ThongTinController {

    @Autowired
    private ThongTinService thongtinService;


@GetMapping("/thongtins")
    public ModelAndView listThongTins(@RequestParam(value = "s", required = false) String s) {
        Iterable<ThongTin> thongtins;
            thongtins = thongtinService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/thongtin/list");
        modelAndView.addObject("thongtins", thongtins);
        return modelAndView;
    }

    @GetMapping("/create-thongtin")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/thongtin/create");
        modelAndView.addObject("thongtin", new ThongTin());
        return modelAndView;
    }
@PostMapping("/create-thongtin")
    public ModelAndView checkValidation(@Valid @ModelAttribute("thongtin") ThongTin thongtin, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/thongtin/create");
            return modelAndView;
        } else {
            thongtinService.create(thongtin.getTen(),thongtin.getTuoi(),thongtin.getCMND(),thongtin.getQueQuan(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/thongtin/create");
            modelAndView.addObject("thongtin", new ThongTin());
            modelAndView.addObject("message", "New thongtin created successfully");
            return modelAndView;
        }
    }

@GetMapping("/edit-thongtin/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ThongTin thongtin = thongtinService.findById(id);
        if (thongtin != null) {
            ModelAndView modelAndView = new ModelAndView("/thongtin/edit");
            modelAndView.addObject("thongtin", thongtin);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
@PostMapping("/edit-thongtin")
    public ModelAndView updateThongTin(@ModelAttribute("thongtin") ThongTin thongtin) {
        thongtinService.edit(thongtin.getTen(),thongtin.getTuoi(),thongtin.getCMND(),thongtin.getQueQuan(), LocalDate.now(), "Dan",thongtin.getId());

        ModelAndView modelAndView = new ModelAndView("/thongtin/edit");
        modelAndView.addObject("thongtin", thongtin);
        modelAndView.addObject("message", "ThongTin updated successfully");
        return modelAndView;
    }

@GetMapping("/delete-thongtin/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        thongtinService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/thongtins";
    }
@GetMapping("/view-thongtin/{id}")
    public ModelAndView viewThongTin(@PathVariable("id") Long id) {
        ThongTin thongtin = thongtinService.findById(id);
        if (thongtin == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/thongtin/view");
        modelAndView.addObject("thongtin", thongtin);
        return modelAndView;}
}