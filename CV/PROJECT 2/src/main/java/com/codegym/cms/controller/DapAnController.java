 package com.codegym.cms.controller; 
 import com.codegym.cms.model.CauHoi;
import com.codegym.cms.service.CauHoiService;
import com.codegym.cms.model.DapAn;
import com.codegym.cms.service.DapAnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class DapAnController {

    @Autowired
    private DapAnService dapanService;

@Autowired
    private CauHoiService cauhoiService;
    @ModelAttribute("cauhois")
    public Iterable<CauHoi> cauhois() {
        return cauhoiService.findAllByIsDeletedEquals(0);
    }

@GetMapping("/dapans")
    public ModelAndView listDapAns(@RequestParam(value = "s", required = false) String s) {
        Iterable<DapAn> dapans;
            dapans = dapanService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/dapan/list");
        modelAndView.addObject("dapans", dapans);
        return modelAndView;
    }

    @GetMapping("/create-dapan")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/dapan/create");
        modelAndView.addObject("dapan", new DapAn());
        return modelAndView;
    }
@PostMapping("/create-dapan")
    public ModelAndView checkValidation(@Valid @ModelAttribute("dapan") DapAn dapan, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/dapan/create");
            return modelAndView;
        } else {
            dapanService.create(dapan.getCauHoi().getId(),dapan.getName(),dapan.getClass1(),dapan.getLuachon(),dapan.getValue(),dapan.getInnerText(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/dapan/create");
            modelAndView.addObject("dapan", new DapAn());
            modelAndView.addObject("message", "New dapan created successfully");
            return modelAndView;
        }
    }

@GetMapping("/edit-dapan/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        DapAn dapan = dapanService.findById(id);
        if (dapan != null) {
            ModelAndView modelAndView = new ModelAndView("/dapan/edit");
            modelAndView.addObject("dapan", dapan);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
@PostMapping("/edit-dapan")
    public ModelAndView updateDapAn(@ModelAttribute("dapan") DapAn dapan) {
        dapanService.edit(dapan.getCauHoi().getId(),dapan.getName(),dapan.getClass1(),dapan.getLuachon(),dapan.getValue(),dapan.getInnerText(), LocalDate.now(), "Dan",dapan.getId());

        ModelAndView modelAndView = new ModelAndView("/dapan/edit");
        modelAndView.addObject("dapan", dapan);
        modelAndView.addObject("message", "DapAn updated successfully");
        return modelAndView;
    }

@GetMapping("/delete-dapan/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        dapanService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/dapans";
    }
@GetMapping("/view-dapan/{id}")
    public ModelAndView viewDapAn(@PathVariable("id") Long id) {
        DapAn dapan = dapanService.findById(id);
        if (dapan == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/dapan/view");
        modelAndView.addObject("dapan", dapan);
        return modelAndView;}
}