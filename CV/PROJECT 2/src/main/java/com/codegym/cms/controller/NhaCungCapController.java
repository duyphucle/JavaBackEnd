 package com.codegym.cms.controller; 
 import com.codegym.cms.model.NhaCungCap;
import com.codegym.cms.service.NhaCungCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class NhaCungCapController {

    @Autowired
    private NhaCungCapService nhacungcapService;


@GetMapping("/nhacungcaps")
    public ModelAndView listNhaCungCaps(@RequestParam(value = "s", required = false) String s) {
        Iterable<NhaCungCap> nhacungcaps;
            nhacungcaps = nhacungcapService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/nhacungcap/list");
        modelAndView.addObject("nhacungcaps", nhacungcaps);
        return modelAndView;
    }

    @GetMapping("/create-nhacungcap")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/nhacungcap/create");
        modelAndView.addObject("nhacungcap", new NhaCungCap());
        return modelAndView;
    }
@PostMapping("/create-nhacungcap")
    public ModelAndView checkValidation(@Valid @ModelAttribute("nhacungcap") NhaCungCap nhacungcap, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/nhacungcap/create");
            return modelAndView;
        } else {
            nhacungcapService.create(nhacungcap.getTenNhaCungCap(),nhacungcap.getDiaChi(),nhacungcap.getThanhPho(),nhacungcap.getQuocGia(),nhacungcap.getMaBuuDien(),nhacungcap.getSDT(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/nhacungcap/create");
            modelAndView.addObject("nhacungcap", new NhaCungCap());
            modelAndView.addObject("message", "New nhacungcap created successfully");
            return modelAndView;
        }
    }

@GetMapping("/edit-nhacungcap/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        NhaCungCap nhacungcap = nhacungcapService.findById(id);
        if (nhacungcap != null) {
            ModelAndView modelAndView = new ModelAndView("/nhacungcap/edit");
            modelAndView.addObject("nhacungcap", nhacungcap);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
@PostMapping("/edit-nhacungcap")
    public ModelAndView updateNhaCungCap(@ModelAttribute("nhacungcap") NhaCungCap nhacungcap) {
        nhacungcapService.edit(nhacungcap.getTenNhaCungCap(),nhacungcap.getDiaChi(),nhacungcap.getThanhPho(),nhacungcap.getQuocGia(),nhacungcap.getMaBuuDien(),nhacungcap.getSDT(), LocalDate.now(), "Dan",nhacungcap.getId());

        ModelAndView modelAndView = new ModelAndView("/nhacungcap/edit");
        modelAndView.addObject("nhacungcap", nhacungcap);
        modelAndView.addObject("message", "NhaCungCap updated successfully");
        return modelAndView;
    }

@GetMapping("/delete-nhacungcap/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        nhacungcapService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/nhacungcaps";
    }
@GetMapping("/view-nhacungcap/{id}")
    public ModelAndView viewNhaCungCap(@PathVariable("id") Long id) {
        NhaCungCap nhacungcap = nhacungcapService.findById(id);
        if (nhacungcap == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/nhacungcap/view");
        modelAndView.addObject("nhacungcap", nhacungcap);
        return modelAndView;}
}