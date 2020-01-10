package com.codegym.cms.controller;

import com.codegym.cms.model.DapAn;
import com.codegym.cms.service.DapAnService;
import com.codegym.cms.model.TraLoi;
import com.codegym.cms.service.TraLoiService;
import com.codegym.cms.model.TraLoiChiTiet;
import com.codegym.cms.service.TraLoiChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class TraLoiChiTietController {

    @Autowired
    private TraLoiChiTietService traloichitietService;

    @Autowired
    private DapAnService dapanService;

    @ModelAttribute("dapans")
    public Iterable<DapAn> dapans() {
        return dapanService.findAllByIsDeletedEquals(0);
    }

    @Autowired
    private TraLoiService traloiService;

    @ModelAttribute("tralois")
    public Iterable<TraLoi> tralois() {
        return traloiService.findAllByIsDeletedEquals(0);
    }

    @GetMapping("/traloichitiets")
    public ModelAndView listTraLoiChiTiets(@RequestParam(value = "s", required = false) String s) {
        Iterable<TraLoiChiTiet> traloichitiets;
        traloichitiets = traloichitietService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/traloichitiet/list");
        modelAndView.addObject("traloichitiets", traloichitiets);
        return modelAndView;
    }

    @GetMapping("/create-traloichitiet")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/traloichitiet/create");
        modelAndView.addObject("traloichitiet", new TraLoiChiTiet());
        return modelAndView;
    }

    @PostMapping("/create-traloichitiet")
    public ModelAndView checkValidation(@Valid @ModelAttribute("traloichitiet") TraLoiChiTiet traloichitiet, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/traloichitiet/create");
            return modelAndView;
        } else {
            traloichitietService.create(traloichitiet.getTraLoi().getId(), traloichitiet.getDapAn().getId(), traloichitiet.getName(), traloichitiet.getLuachon(), traloichitiet.getValue(), traloichitiet.getInnerText(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/traloichitiet/create");
            modelAndView.addObject("traloichitiet", new TraLoiChiTiet());
            modelAndView.addObject("message", "New traloichitiet created successfully");
            return modelAndView;
        }
    }

    @GetMapping("/edit-traloichitiet/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        TraLoiChiTiet traloichitiet = traloichitietService.findById(id);
        if (traloichitiet != null) {
            ModelAndView modelAndView = new ModelAndView("/traloichitiet/edit");
            modelAndView.addObject("traloichitiet", traloichitiet);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-traloichitiet")
    public ModelAndView updateTraLoiChiTiet(@ModelAttribute("traloichitiet") TraLoiChiTiet traloichitiet) {
        traloichitietService.edit(traloichitiet.getTraLoi().getId(), traloichitiet.getDapAn().getId(), traloichitiet.getName(), traloichitiet.getLuachon(), traloichitiet.getValue(), traloichitiet.getInnerText(), LocalDate.now(), "Dan", traloichitiet.getId());

        ModelAndView modelAndView = new ModelAndView("/traloichitiet/edit");
        modelAndView.addObject("traloichitiet", traloichitiet);
        modelAndView.addObject("message", "TraLoiChiTiet updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-traloichitiet/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        traloichitietService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/traloichitiets";
    }

    @GetMapping("/view-traloichitiet/{id}")
    public ModelAndView viewTraLoiChiTiet(@PathVariable("id") Long id) {
        TraLoiChiTiet traloichitiet = traloichitietService.findById(id);
        if (traloichitiet == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/traloichitiet/view");
        modelAndView.addObject("traloichitiet", traloichitiet);
        return modelAndView;
    }
}