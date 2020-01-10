 package com.codegym.cms.controller; 
 import com.codegym.cms.model.Ngay;
import com.codegym.cms.service.NgayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class NgayController {

    @Autowired
    private NgayService ngayService;


@GetMapping("/ngays")
    public ModelAndView listNgays(@RequestParam(value = "s", required = false) String s) {
        Iterable<Ngay> ngays;
            ngays = ngayService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/ngay/list");
        modelAndView.addObject("ngays", ngays);
        return modelAndView;
    }

    @GetMapping("/create-ngay")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/ngay/create");
        modelAndView.addObject("ngay", new Ngay());
        return modelAndView;
    }
@PostMapping("/create-ngay")
    public ModelAndView checkValidation(@Valid @ModelAttribute("ngay") Ngay ngay, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/ngay/create");
            return modelAndView;
        } else {
            ngayService.create(ngay.getName(),ngay.getClass1(),ngay.getValue(),ngay.getLuachon(),ngay.getInnerText(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/ngay/create");
            modelAndView.addObject("ngay", new Ngay());
            modelAndView.addObject("message", "New ngay created successfully");
            return modelAndView;
        }
    }

@GetMapping("/edit-ngay/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Ngay ngay = ngayService.findById(id);
        if (ngay != null) {
            ModelAndView modelAndView = new ModelAndView("/ngay/edit");
            modelAndView.addObject("ngay", ngay);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
@PostMapping("/edit-ngay")
    public ModelAndView updateNgay(@ModelAttribute("ngay") Ngay ngay) {
        ngayService.edit(ngay.getName(),ngay.getClass1(),ngay.getValue(),ngay.getLuachon(),ngay.getInnerText(), LocalDate.now(), "Dan",ngay.getId());

        ModelAndView modelAndView = new ModelAndView("/ngay/edit");
        modelAndView.addObject("ngay", ngay);
        modelAndView.addObject("message", "Ngay updated successfully");
        return modelAndView;
    }

@GetMapping("/delete-ngay/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        ngayService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/ngays";
    }
@GetMapping("/view-ngay/{id}")
    public ModelAndView viewNgay(@PathVariable("id") Long id) {
        Ngay ngay = ngayService.findById(id);
        if (ngay == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/ngay/view");
        modelAndView.addObject("ngay", ngay);
        return modelAndView;}
}