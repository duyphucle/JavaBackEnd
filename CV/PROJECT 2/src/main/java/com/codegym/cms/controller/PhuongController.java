 package com.codegym.cms.controller; 
 import com.codegym.cms.model.Phuong;
import com.codegym.cms.service.PhuongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class PhuongController {

    @Autowired
    private PhuongService phuongService;


@GetMapping("/phuongs")
    public ModelAndView listPhuongs(@RequestParam(value = "s", required = false) String s) {
        Iterable<Phuong> phuongs;
            phuongs = phuongService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/phuong/list");
        modelAndView.addObject("phuongs", phuongs);
        return modelAndView;
    }

    @GetMapping("/create-phuong")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/phuong/create");
        modelAndView.addObject("phuong", new Phuong());
        return modelAndView;
    }
@PostMapping("/create-phuong")
    public ModelAndView checkValidation(@Valid @ModelAttribute("phuong") Phuong phuong, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/phuong/create");
            return modelAndView;
        } else {
            phuongService.create(phuong.getTenPhuong(),phuong.getHuyen_id(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/phuong/create");
            modelAndView.addObject("phuong", new Phuong());
            modelAndView.addObject("message", "New phuong created successfully");
            return modelAndView;
        }
    }

@GetMapping("/edit-phuong/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Phuong phuong = phuongService.findById(id);
        if (phuong != null) {
            ModelAndView modelAndView = new ModelAndView("/phuong/edit");
            modelAndView.addObject("phuong", phuong);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
@PostMapping("/edit-phuong")
    public ModelAndView updatePhuong(@ModelAttribute("phuong") Phuong phuong) {
        phuongService.edit(phuong.getTenPhuong(),phuong.getHuyen_id(), LocalDate.now(), "Dan",phuong.getId());

        ModelAndView modelAndView = new ModelAndView("/phuong/edit");
        modelAndView.addObject("phuong", phuong);
        modelAndView.addObject("message", "Phuong updated successfully");
        return modelAndView;
    }

@GetMapping("/delete-phuong/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        phuongService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/phuongs";
    }
@GetMapping("/view-phuong/{id}")
    public ModelAndView viewPhuong(@PathVariable("id") Long id) {
        Phuong phuong = phuongService.findById(id);
        if (phuong == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/phuong/view");
        modelAndView.addObject("phuong", phuong);
        return modelAndView;}
}