package com.codegym.cms.controller;

import com.codegym.cms.model.Survey;
import com.codegym.cms.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class
SurveyController {

    @Autowired
    private SurveyService surveyService;


    @GetMapping("/surveys")
    public ModelAndView listSurveys(@RequestParam(value = "s", required = false) String s) {
        Iterable<Survey> surveys;
        surveys = surveyService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/survey/list");
        modelAndView.addObject("surveys", surveys);
        return modelAndView;
    }

    @GetMapping("/create-survey")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/survey/create");
        modelAndView.addObject("survey", new Survey());
        return modelAndView;
    }

    @PostMapping("/create-survey")
    public ModelAndView checkValidation(@Valid @ModelAttribute("survey") Survey survey, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/survey/create");
            return modelAndView;
        } else {
            surveyService.create(survey.getTenkhaosat(), survey.getDiem(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/survey/create");
            modelAndView.addObject("survey", new Survey());
            modelAndView.addObject("message", "New survey created successfully");
            return modelAndView;
        }
    }

    @GetMapping("/edit-survey/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Survey survey = surveyService.findById(id);
        if (survey != null) {
            ModelAndView modelAndView = new ModelAndView("/survey/edit");
            modelAndView.addObject("survey", survey);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-survey")
    public ModelAndView updateSurvey(@ModelAttribute("survey") Survey survey) {
        surveyService.edit(survey.getTenkhaosat(), survey.getDiem(), LocalDate.now(), "Dan", survey.getId());

        ModelAndView modelAndView = new ModelAndView("/survey/edit");
        modelAndView.addObject("survey", survey);
        modelAndView.addObject("message", "Survey updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-survey/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        surveyService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/surveys";
    }

    @GetMapping("/view-survey/{id}")
    public ModelAndView viewSurvey(@PathVariable("id") Long id) {
        Survey survey = surveyService.findById(id);
        if (survey == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/survey/view");
        modelAndView.addObject("survey", survey);
        return modelAndView;
    }
}