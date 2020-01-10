 package com.codegym.cms.controller; 
 import com.codegym.cms.model.Sell;
import com.codegym.cms.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class SellController {

    @Autowired
    private SellService sellService;


@GetMapping("/sells")
    public ModelAndView listSells(@RequestParam(value = "s", required = false) String s) {
        Iterable<Sell> sells;
            sells = sellService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/sell/list");
        modelAndView.addObject("sells", sells);
        return modelAndView;
    }

    @GetMapping("/create-sell")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/sell/create");
        modelAndView.addObject("sell", new Sell());
        return modelAndView;
    }
@PostMapping("/create-sell")
    public ModelAndView checkValidation(@Valid @ModelAttribute("sell") Sell sell, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/sell/create");
            return modelAndView;
        } else {
            sellService.create(sell.getTenChienDich(),sell.getPhanTramSale(),sell.getNgayBatDau(),sell.getNgayKetThuc(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/sell/create");
            modelAndView.addObject("sell", new Sell());
            modelAndView.addObject("message", "New sell created successfully");
            return modelAndView;
        }
    }

@GetMapping("/edit-sell/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Sell sell = sellService.findById(id);
        if (sell != null) {
            ModelAndView modelAndView = new ModelAndView("/sell/edit");
            modelAndView.addObject("sell", sell);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
@PostMapping("/edit-sell")
    public ModelAndView updateSell(@ModelAttribute("sell") Sell sell) {
        sellService.edit(sell.getTenChienDich(),sell.getPhanTramSale(),sell.getNgayBatDau(),sell.getNgayKetThuc(), LocalDate.now(), "Dan",sell.getId());

        ModelAndView modelAndView = new ModelAndView("/sell/edit");
        modelAndView.addObject("sell", sell);
        modelAndView.addObject("message", "Sell updated successfully");
        return modelAndView;
    }

@GetMapping("/delete-sell/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        sellService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/sells";
    }
@GetMapping("/view-sell/{id}")
    public ModelAndView viewSell(@PathVariable("id") Long id) {
        Sell sell = sellService.findById(id);
        if (sell == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/sell/view");
        modelAndView.addObject("sell", sell);
        return modelAndView;}
}