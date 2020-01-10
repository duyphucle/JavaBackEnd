 package com.codegym.cms.controller; 
 import com.codegym.cms.model.Xe;
import com.codegym.cms.service.XeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class XeController {

    @Autowired
    private XeService xeService;


@GetMapping("/xes")
    public ModelAndView listXes(@RequestParam(value = "s", required = false) String s) {
        Iterable<Xe> xes;
            xes = xeService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/xe/list");
        modelAndView.addObject("xes", xes);
        return modelAndView;
    }

    @GetMapping("/create-xe")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/xe/create");
        modelAndView.addObject("xe", new Xe());
        return modelAndView;
    }
@PostMapping("/create-xe")
    public ModelAndView checkValidation(@Valid @ModelAttribute("xe") Xe xe, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/xe/create");
            return modelAndView;
        } else {
            xeService.create(xe.getDongXe(),xe.getIdHang(),xe.getHangXe(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/xe/create");
            modelAndView.addObject("xe", new Xe());
            modelAndView.addObject("message", "New xe created successfully");
            return modelAndView;
        }
    }

@GetMapping("/edit-xe/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Xe xe = xeService.findById(id);
        if (xe != null) {
            ModelAndView modelAndView = new ModelAndView("/xe/edit");
            modelAndView.addObject("xe", xe);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
@PostMapping("/edit-xe")
    public ModelAndView updateXe(@ModelAttribute("xe") Xe xe) {
        xeService.edit(xe.getDongXe(),xe.getIdHang(),xe.getHangXe(), LocalDate.now(), "Dan",xe.getId());

        ModelAndView modelAndView = new ModelAndView("/xe/edit");
        modelAndView.addObject("xe", xe);
        modelAndView.addObject("message", "Xe updated successfully");
        return modelAndView;
    }

@GetMapping("/delete-xe/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        xeService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/xes";
    }
@GetMapping("/view-xe/{id}")
    public ModelAndView viewXe(@PathVariable("id") Long id) {
        Xe xe = xeService.findById(id);
        if (xe == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/xe/view");
        modelAndView.addObject("xe", xe);
        return modelAndView;}
}