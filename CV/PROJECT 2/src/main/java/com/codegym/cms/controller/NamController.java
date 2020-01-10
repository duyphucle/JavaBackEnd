 package com.codegym.cms.controller; 
 import com.codegym.cms.model.Nam;
import com.codegym.cms.service.NamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class NamController {

    @Autowired
    private NamService namService;


@GetMapping("/nams")
    public ModelAndView listNams(@RequestParam(value = "s", required = false) String s) {
        Iterable<Nam> nams;
            nams = namService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/nam/list");
        modelAndView.addObject("nams", nams);
        return modelAndView;
    }

    @GetMapping("/create-nam")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/nam/create");
        modelAndView.addObject("nam", new Nam());
        return modelAndView;
    }
@PostMapping("/create-nam")
    public ModelAndView checkValidation(@Valid @ModelAttribute("nam") Nam nam, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/nam/create");
            return modelAndView;
        } else {
            namService.create(nam.getName(),nam.getClass1(),nam.getValue(),nam.getLuachon(),nam.getInnerText(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/nam/create");
            modelAndView.addObject("nam", new Nam());
            modelAndView.addObject("message", "New nam created successfully");
            return modelAndView;
        }
    }

@GetMapping("/edit-nam/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Nam nam = namService.findById(id);
        if (nam != null) {
            ModelAndView modelAndView = new ModelAndView("/nam/edit");
            modelAndView.addObject("nam", nam);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
@PostMapping("/edit-nam")
    public ModelAndView updateNam(@ModelAttribute("nam") Nam nam) {
        namService.edit(nam.getName(),nam.getClass1(),nam.getValue(),nam.getLuachon(),nam.getInnerText(), LocalDate.now(), "Dan",nam.getId());

        ModelAndView modelAndView = new ModelAndView("/nam/edit");
        modelAndView.addObject("nam", nam);
        modelAndView.addObject("message", "Nam updated successfully");
        return modelAndView;
    }

@GetMapping("/delete-nam/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        namService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/nams";
    }
@GetMapping("/view-nam/{id}")
    public ModelAndView viewNam(@PathVariable("id") Long id) {
        Nam nam = namService.findById(id);
        if (nam == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/nam/view");
        modelAndView.addObject("nam", nam);
        return modelAndView;}
}