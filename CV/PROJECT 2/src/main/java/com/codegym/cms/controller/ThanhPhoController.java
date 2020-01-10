 package com.codegym.cms.controller; 
 import com.codegym.cms.model.ThanhPho;
import com.codegym.cms.service.ThanhPhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class ThanhPhoController {

    @Autowired
    private ThanhPhoService thanhphoService;


@GetMapping("/thanhphos")
    public ModelAndView listThanhPhos(@RequestParam(value = "s", required = false) String s) {
        Iterable<ThanhPho> thanhphos;
            thanhphos = thanhphoService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/thanhpho/list");
        modelAndView.addObject("thanhphos", thanhphos);
        return modelAndView;
    }

    @GetMapping("/create-thanhpho")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/thanhpho/create");
        modelAndView.addObject("thanhpho", new ThanhPho());
        return modelAndView;
    }
@PostMapping("/create-thanhpho")
    public ModelAndView checkValidation(@Valid @ModelAttribute("thanhpho") ThanhPho thanhpho, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/thanhpho/create");
            return modelAndView;
        } else {
            thanhphoService.create(thanhpho.getThanhPho(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/thanhpho/create");
            modelAndView.addObject("thanhpho", new ThanhPho());
            modelAndView.addObject("message", "New thanhpho created successfully");
            return modelAndView;
        }
    }

@GetMapping("/edit-thanhpho/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ThanhPho thanhpho = thanhphoService.findById(id);
        if (thanhpho != null) {
            ModelAndView modelAndView = new ModelAndView("/thanhpho/edit");
            modelAndView.addObject("thanhpho", thanhpho);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
@PostMapping("/edit-thanhpho")
    public ModelAndView updateThanhPho(@ModelAttribute("thanhpho") ThanhPho thanhpho) {
        thanhphoService.edit(thanhpho.getThanhPho(), LocalDate.now(), "Dan",thanhpho.getId());

        ModelAndView modelAndView = new ModelAndView("/thanhpho/edit");
        modelAndView.addObject("thanhpho", thanhpho);
        modelAndView.addObject("message", "ThanhPho updated successfully");
        return modelAndView;
    }

@GetMapping("/delete-thanhpho/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        thanhphoService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/thanhphos";
    }
@GetMapping("/view-thanhpho/{id}")
    public ModelAndView viewThanhPho(@PathVariable("id") Long id) {
        ThanhPho thanhpho = thanhphoService.findById(id);
        if (thanhpho == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/thanhpho/view");
        modelAndView.addObject("thanhpho", thanhpho);
        return modelAndView;}
}