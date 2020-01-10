package com.codegym.cms.formatter;

import com.codegym.cms.model.UserDoingSurvey;
import com.codegym.cms.service.UserDoingSurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class UserDoingSurveyFormatter implements Formatter<UserDoingSurvey> {

    private UserDoingSurveyService userdoingsurveyService;

    @Autowired
    public UserDoingSurveyFormatter(UserDoingSurveyService userdoingsurveyService) {
        this.userdoingsurveyService = userdoingsurveyService;
    }

    @Override
    public UserDoingSurvey parse(String text, Locale locale) throws ParseException {
        return userdoingsurveyService.findById(Long.parseLong(text));
    }

    @Override
    public String print(UserDoingSurvey object, Locale locale) {
        return null;  }
}
 
