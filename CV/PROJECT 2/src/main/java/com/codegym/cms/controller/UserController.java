 package com.codegym.cms.controller; 
 import com.codegym.cms.model.ThongTin;
import com.codegym.cms.service.ThongTinService;
import com.codegym.cms.model.User;
import com.codegym.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

@Autowired
    private ThongTinService thongtinService;
    @ModelAttribute("thongtins")
    public Iterable<ThongTin> thongtins() {
        return thongtinService.findAllByIsDeletedEquals(0);
    }

@GetMapping("/users")
    public ModelAndView listUsers(@RequestParam(value = "s", required = false) String s) {
        Iterable<User> users;
            users = userService.findAllByIsDeletedEquals(0);

        ModelAndView modelAndView = new ModelAndView("/user/list");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/create-user")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/user/create");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }
@PostMapping("/create-user")
    public ModelAndView checkValidation(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/user/create");
            return modelAndView;
        } else {
            userService.create(user.getUsername(),user.getTongdiem(),user.getDiemdarut(),user.getDiemchoduyet(),user.getThongTin().getId(),user.getEmail(),user.getPassword(), LocalDate.now(), "Dan");

            ModelAndView modelAndView = new ModelAndView("/user/create");
            modelAndView.addObject("user", new User());
            modelAndView.addObject("message", "New user created successfully");
            return modelAndView;
        }
    }

@GetMapping("/edit-user/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user != null) {
            ModelAndView modelAndView = new ModelAndView("/user/edit");
            modelAndView.addObject("user", user);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
@PostMapping("/edit-user")
    public ModelAndView updateUser(@ModelAttribute("user") User user) {
        userService.edit(user.getUsername(),user.getTongdiem(),user.getDiemdarut(),user.getDiemchoduyet(),user.getThongTin().getId(),user.getEmail(),user.getPassword(), LocalDate.now(), "Dan",user.getId());

        ModelAndView modelAndView = new ModelAndView("/user/edit");
        modelAndView.addObject("user", user);
        modelAndView.addObject("message", "User updated successfully");
        return modelAndView;
    }

@GetMapping("/delete-user/{id}")
    public String showDeleteForm(@PathVariable Long id) {
        userService.softDelete(LocalDate.now(), "Dan3", id);
        return "redirect:/users";
    }
@GetMapping("/view-user/{id}")
    public ModelAndView viewUser(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ModelAndView("/error.404");
        }

        ModelAndView modelAndView = new ModelAndView("/user/view");
        modelAndView.addObject("user", user);
        return modelAndView;}
}