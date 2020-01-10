package com.codegym.cms.controller;

import com.codegym.cms.model.CauHoi;
import com.codegym.cms.service.CauHoiService;
import com.codegym.cms.model.UserDoingSurvey;
import com.codegym.cms.service.UserDoingSurveyService;
import com.codegym.cms.model.TraLoi;
import com.codegym.cms.service.TraLoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class TraLoiController {

    @Autowired
    private TraLoiService traloiService;

    @Autowired
    private CauHoiService cauhoiService;

    @ModelAttribute("cauhois")
    public Iterable<CauHoi> cauhois() {
        return cauhoiService.findAllByIsDeletedEquals(0);
    }

    @Autowired
    private UserDoingSurveyService userdoingsurveyService;

    @ModelAttribute("userdoingsurveys")
    public Iterable<UserDoingSurvey> userdoingsurveys() {
        return userdoingsurveyService.findAllByIsDeletedEquals(0);
    }

    @GetMapping("/tralois")
    public ModelAndView listTraLois(@RequestParam(value = "s", required = false) String s) {
        Iterable<TraLoi> tralois;
        tralois = traloiService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/traloi/list");
        modelAndView.addObject("tralois", tralois);
        return modelAndView;
    }

    @GetMapping("/create-traloi")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/traloi/create");
        modelAndView.addObject("traloi", new TraLoi());
        return modelAndView;
    }

    @PostMapping("/create-traloi")
    public ModelAndView checkValidation(@Valid @ModelAttribute("traloi") TraLoi traloi, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/traloi/create");
            return modelAndView;
        } else {
            traloiService.create(traloi.getUserDoingSurvey().getId(), traloi.getCauHoi().getId(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/traloi/create");
            modelAndView.addObject("traloi", new TraLoi());
            modelAndView.addObject("message", "New traloi created successfully");
            return modelAndView;
        }
    }

    @GetMapping("/edit-traloi/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        TraLoi traloi = traloiService.findById(id);
        if (traloi != null) {
            ModelAndView modelAndView = new ModelAndView("/traloi/edit");
            modelAndView.addObject("traloi", traloi);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-traloi")
    public ModelAndView updateTraLoi(@ModelAttribute("traloi") TraLoi traloi) {
        traloiService.edit(traloi.getUserDoingSurvey().getId(), traloi.getCauHoi().getId(), LocalDate.now(), "Dan", traloi.getId());

        ModelAndView modelAndView = new ModelAndView("/traloi/edit");
        modelAndView.addObject("traloi", traloi);
        modelAndView.addObject("message", "TraLoi updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-traloi/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        traloiService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/tralois";
    }

    @GetMapping("/view-traloi/{id}")
    public ModelAndView viewTraLoi(@PathVariable("id") Long id) {
        TraLoi traloi = traloiService.findById(id);
        if (traloi == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/traloi/view");
        modelAndView.addObject("traloi", traloi);
        return modelAndView;
    }
}