 package com.codegym.cms.controller; 
 import com.codegym.cms.model.ThanhPho;
import com.codegym.cms.service.ThanhPhoService;
import com.codegym.cms.model.Huyen;
import com.codegym.cms.service.HuyenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class HuyenController {

    @Autowired
    private HuyenService huyenService;

@Autowired
    private ThanhPhoService thanhphoService;
    @ModelAttribute("thanhphos")
    public Iterable<ThanhPho> thanhphos() {
        return thanhphoService.findAllByIsDeletedEquals(0);
    }

@GetMapping("/huyens")
    public ModelAndView listHuyens(@RequestParam(value = "s", required = false) String s) {
        Iterable<Huyen> huyens;
            huyens = huyenService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/huyen/list");
        modelAndView.addObject("huyens", huyens);
        return modelAndView;
    }

    @GetMapping("/create-huyen")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/huyen/create");
        modelAndView.addObject("huyen", new Huyen());
        return modelAndView;
    }
@PostMapping("/create-huyen")
    public ModelAndView checkValidation(@Valid @ModelAttribute("huyen") Huyen huyen, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/huyen/create");
            return modelAndView;
        } else {
            huyenService.create(huyen.getTenHuyen(),huyen.getThanhPho().getId(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/huyen/create");
            modelAndView.addObject("huyen", new Huyen());
            modelAndView.addObject("message", "New huyen created successfully");
            return modelAndView;
        }
    }

@GetMapping("/edit-huyen/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Huyen huyen = huyenService.findById(id);
        if (huyen != null) {
            ModelAndView modelAndView = new ModelAndView("/huyen/edit");
            modelAndView.addObject("huyen", huyen);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
@PostMapping("/edit-huyen")
    public ModelAndView updateHuyen(@ModelAttribute("huyen") Huyen huyen) {
        huyenService.edit(huyen.getTenHuyen(),huyen.getThanhPho().getId(), LocalDate.now(), "Dan",huyen.getId());

        ModelAndView modelAndView = new ModelAndView("/huyen/edit");
        modelAndView.addObject("huyen", huyen);
        modelAndView.addObject("message", "Huyen updated successfully");
        return modelAndView;
    }

@GetMapping("/delete-huyen/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        huyenService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/huyens";
    }
@GetMapping("/view-huyen/{id}")
    public ModelAndView viewHuyen(@PathVariable("id") Long id) {
        Huyen huyen = huyenService.findById(id);
        if (huyen == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/huyen/view");
        modelAndView.addObject("huyen", huyen);
        return modelAndView;}
}