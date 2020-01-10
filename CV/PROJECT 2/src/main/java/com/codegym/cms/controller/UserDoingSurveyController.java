package com.codegym.cms.controller;

import com.codegym.cms.model.Survey;
import com.codegym.cms.service.SurveyService;
import com.codegym.cms.model.User;
import com.codegym.cms.service.UserService;
import com.codegym.cms.model.UserDoingSurvey;
import com.codegym.cms.service.UserDoingSurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class UserDoingSurveyController {

    @Autowired
    private UserDoingSurveyService userdoingsurveyService;

    @Autowired
    private SurveyService surveyService;

    @ModelAttribute("surveys")
    public Iterable<Survey> surveys() {
        return surveyService.findAllByIsDeletedEquals(0);
    }

    @Autowired
    private UserService userService;

    @ModelAttribute("users")
    public Iterable<User> users() {
        return userService.findAllByIsDeletedEquals(0);
    }

    @GetMapping("/userdoingsurveys")
    public ModelAndView listUserDoingSurveys(@RequestParam(value = "s", required = false) String s) {
        Iterable<UserDoingSurvey> userdoingsurveys;
        userdoingsurveys = userdoingsurveyService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/userdoingsurvey/list");
        modelAndView.addObject("userdoingsurveys", userdoingsurveys);
        return modelAndView;
    }

    @GetMapping("/create-userdoingsurvey")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/userdoingsurvey/create");
        modelAndView.addObject("userdoingsurvey", new UserDoingSurvey());
        return modelAndView;
    }

    @PostMapping("/create-userdoingsurvey")
    public ModelAndView checkValidation(@Valid @ModelAttribute("userdoingsurvey") UserDoingSurvey userdoingsurvey, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/userdoingsurvey/create");
            return modelAndView;
        } else {
            userdoingsurveyService.create(userdoingsurvey.getUser().getId(), userdoingsurvey.getSurvey().getId(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/userdoingsurvey/create");
            modelAndView.addObject("userdoingsurvey", new UserDoingSurvey());
            modelAndView.addObject("message", "New userdoingsurvey created successfully");
            return modelAndView;
        }
    }

    @GetMapping("/edit-userdoingsurvey/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        UserDoingSurvey userdoingsurvey = userdoingsurveyService.findById(id);
        if (userdoingsurvey != null) {
            ModelAndView modelAndView = new ModelAndView("/userdoingsurvey/edit");
            modelAndView.addObject("userdoingsurvey", userdoingsurvey);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-userdoingsurvey")
    public ModelAndView updateUserDoingSurvey(@ModelAttribute("userdoingsurvey") UserDoingSurvey userdoingsurvey) {
        userdoingsurveyService.edit(userdoingsurvey.getUser().getId(), userdoingsurvey.getSurvey().getId(), LocalDate.now(), "Dan", userdoingsurvey.getId());

        ModelAndView modelAndView = new ModelAndView("/userdoingsurvey/edit");
        modelAndView.addObject("userdoingsurvey", userdoingsurvey);
        modelAndView.addObject("message", "UserDoingSurvey updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-userdoingsurvey/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        userdoingsurveyService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/userdoingsurveys";
    }

    @GetMapping("/view-userdoingsurvey/{id}")
    public ModelAndView viewUserDoingSurvey(@PathVariable("id") Long id) {
        UserDoingSurvey userdoingsurvey = userdoingsurveyService.findById(id);
        if (userdoingsurvey == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/userdoingsurvey/view");
        modelAndView.addObject("userdoingsurvey", userdoingsurvey);
        return modelAndView;
    }
}