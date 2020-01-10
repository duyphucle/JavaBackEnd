package com.codegym.cms.formatter;

import com.codegym.cms.model.Survey;
import com.codegym.cms.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class SurveyFormatter implements Formatter<Survey> {

    private SurveyService surveyService;

    @Autowired
    public SurveyFormatter(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @Override
    public Survey parse(String text, Locale locale) throws ParseException {
        return surveyService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Survey object, Locale locale) {
        return null;  }
}
 
