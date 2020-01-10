package com.codegym.cms.formatter;

import com.codegym.cms.model.User;
import com.codegym.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class UserFormatter implements Formatter<User> {

    private UserService userService;

    @Autowired
    public UserFormatter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User parse(String text, Locale locale) throws ParseException {
        return userService.findById(Long.parseLong(text));
    }

    @Override
    public String print(User object, Locale locale) {
        return null;  }
}
 
