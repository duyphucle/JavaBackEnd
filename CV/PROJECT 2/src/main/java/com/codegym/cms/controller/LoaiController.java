 package com.codegym.cms.controller; 
 import com.codegym.cms.model.Loai;
import com.codegym.cms.service.LoaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class LoaiController {

    @Autowired
    private LoaiService loaiService;


@GetMapping("/loais")
    public ModelAndView listLoais(@RequestParam(value = "s", required = false) String s) {
        Iterable<Loai> loais;
            loais = loaiService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/loai/list");
        modelAndView.addObject("loais", loais);
        return modelAndView;
    }

    @GetMapping("/create-loai")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/loai/create");
        modelAndView.addObject("loai", new Loai());
        return modelAndView;
    }
@PostMapping("/create-loai")
    public ModelAndView checkValidation(@Valid @ModelAttribute("loai") Loai loai, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/loai/create");
            return modelAndView;
        } else {
            loaiService.create(loai.getTenLoai(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/loai/create");
            modelAndView.addObject("loai", new Loai());
            modelAndView.addObject("message", "New loai created successfully");
            return modelAndView;
        }
    }

@GetMapping("/edit-loai/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Loai loai = loaiService.findById(id);
        if (loai != null) {
            ModelAndView modelAndView = new ModelAndView("/loai/edit");
            modelAndView.addObject("loai", loai);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
@PostMapping("/edit-loai")
    public ModelAndView updateLoai(@ModelAttribute("loai") Loai loai) {
        loaiService.edit(loai.getTenLoai(), LocalDate.now(), "Dan",loai.getId());

        ModelAndView modelAndView = new ModelAndView("/loai/edit");
        modelAndView.addObject("loai", loai);
        modelAndView.addObject("message", "Loai updated successfully");
        return modelAndView;
    }

@GetMapping("/delete-loai/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        loaiService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/loais";
    }
@GetMapping("/view-loai/{id}")
    public ModelAndView viewLoai(@PathVariable("id") Long id) {
        Loai loai = loaiService.findById(id);
        if (loai == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/loai/view");
        modelAndView.addObject("loai", loai);
        return modelAndView;}
}