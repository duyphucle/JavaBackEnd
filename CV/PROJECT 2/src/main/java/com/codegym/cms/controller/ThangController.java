 package com.codegym.cms.controller; 
 import com.codegym.cms.model.Thang;
import com.codegym.cms.service.ThangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class ThangController {

    @Autowired
    private ThangService thangService;


@GetMapping("/thangs")
    public ModelAndView listThangs(@RequestParam(value = "s", required = false) String s) {
        Iterable<Thang> thangs;
            thangs = thangService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/thang/list");
        modelAndView.addObject("thangs", thangs);
        return modelAndView;
    }

    @GetMapping("/create-thang")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/thang/create");
        modelAndView.addObject("thang", new Thang());
        return modelAndView;
    }
@PostMapping("/create-thang")
    public ModelAndView checkValidation(@Valid @ModelAttribute("thang") Thang thang, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/thang/create");
            return modelAndView;
        } else {
            thangService.create(thang.getName(),thang.getClass1(),thang.getValue(),thang.getLuachon(),thang.getInnerText(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/thang/create");
            modelAndView.addObject("thang", new Thang());
            modelAndView.addObject("message", "New thang created successfully");
            return modelAndView;
        }
    }

@GetMapping("/edit-thang/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Thang thang = thangService.findById(id);
        if (thang != null) {
            ModelAndView modelAndView = new ModelAndView("/thang/edit");
            modelAndView.addObject("thang", thang);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
@PostMapping("/edit-thang")
    public ModelAndView updateThang(@ModelAttribute("thang") Thang thang) {
        thangService.edit(thang.getName(),thang.getClass1(),thang.getValue(),thang.getLuachon(),thang.getInnerText(), LocalDate.now(), "Dan",thang.getId());

        ModelAndView modelAndView = new ModelAndView("/thang/edit");
        modelAndView.addObject("thang", thang);
        modelAndView.addObject("message", "Thang updated successfully");
        return modelAndView;
    }

@GetMapping("/delete-thang/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        thangService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/thangs";
    }
@GetMapping("/view-thang/{id}")
    public ModelAndView viewThang(@PathVariable("id") Long id) {
        Thang thang = thangService.findById(id);
        if (thang == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/thang/view");
        modelAndView.addObject("thang", thang);
        return modelAndView;}
}