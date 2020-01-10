 package com.codegym.cms.controller; 
 import com.codegym.cms.model.Loai;
import com.codegym.cms.service.LoaiService;
import com.codegym.cms.model.Survey;
import com.codegym.cms.service.SurveyService;
import com.codegym.cms.model.CauHoi;
import com.codegym.cms.service.CauHoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class CauHoiController {

    @Autowired
    private CauHoiService cauhoiService;

@Autowired
    private LoaiService loaiService;
    @ModelAttribute("loais")
    public Iterable<Loai> loais() {
        return loaiService.findAllByIsDeletedEquals(0);
    }
@Autowired
    private SurveyService surveyService;
    @ModelAttribute("surveys")
    public Iterable<Survey> surveys() {
        return surveyService.findAllByIsDeletedEquals(0);
    }

@GetMapping("/cauhois")
    public ModelAndView listCauHois(@RequestParam(value = "s", required = false) String s) {
        Iterable<CauHoi> cauhois;
            cauhois = cauhoiService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/cauhoi/list");
        modelAndView.addObject("cauhois", cauhois);
        return modelAndView;
    }

    @GetMapping("/create-cauhoi")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/cauhoi/create");
        modelAndView.addObject("cauhoi", new CauHoi());
        return modelAndView;
    }
@PostMapping("/create-cauhoi")
    public ModelAndView checkValidation(@Valid @ModelAttribute("cauhoi") CauHoi cauhoi, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/cauhoi/create");
            return modelAndView;
        } else {
            cauhoiService.create(cauhoi.getTencauhoi(),cauhoi.getSite(),cauhoi.getLoai().getId(),cauhoi.getSurvey().getId(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/cauhoi/create");
            modelAndView.addObject("cauhoi", new CauHoi());
            modelAndView.addObject("message", "New cauhoi created successfully");
            return modelAndView;
        }
    }

@GetMapping("/edit-cauhoi/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        CauHoi cauhoi = cauhoiService.findById(id);
        if (cauhoi != null) {
            ModelAndView modelAndView = new ModelAndView("/cauhoi/edit");
            modelAndView.addObject("cauhoi", cauhoi);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
@PostMapping("/edit-cauhoi")
    public ModelAndView updateCauHoi(@ModelAttribute("cauhoi") CauHoi cauhoi) {
        cauhoiService.edit(cauhoi.getTencauhoi(),cauhoi.getSite(),cauhoi.getLoai().getId(),cauhoi.getSurvey().getId(), LocalDate.now(), "Dan",cauhoi.getId());

        ModelAndView modelAndView = new ModelAndView("/cauhoi/edit");
        modelAndView.addObject("cauhoi", cauhoi);
        modelAndView.addObject("message", "CauHoi updated successfully");
        return modelAndView;
    }

@GetMapping("/delete-cauhoi/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        cauhoiService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/cauhois";
    }
@GetMapping("/view-cauhoi/{id}")
    public ModelAndView viewCauHoi(@PathVariable("id") Long id) {
        CauHoi cauhoi = cauhoiService.findById(id);
        if (cauhoi == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/cauhoi/view");
        modelAndView.addObject("cauhoi", cauhoi);
        return modelAndView;}
}