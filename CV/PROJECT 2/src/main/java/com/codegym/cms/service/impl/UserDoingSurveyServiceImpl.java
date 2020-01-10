package com.codegym.cms.service.impl;

import com.codegym.cms.model.Survey;
import com.codegym.cms.model.User;
import com.codegym.cms.model.UserDoingSurvey;
import com.codegym.cms.repository.UserDoingSurveyRepository;
import com.codegym.cms.service.UserDoingSurveyService;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class UserDoingSurveyServiceImpl implements UserDoingSurveyService {
    @Autowired
    private UserDoingSurveyRepository userdoingsurveyRepository;

    @Override
    public UserDoingSurvey findById(Long id) {
        return userdoingsurveyRepository.findById(id).get();
    }

    @Override
    public Iterable<UserDoingSurvey> findAllByIsDeletedEquals(int isDeleted) {
        return userdoingsurveyRepository.findAllByIsDeletedEquals(isDeleted);
    }

    @Override
    public void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id) {
        userdoingsurveyRepository.softDelete(deleted_at, deleted_by, id);
    }


    @Override
    public void create(@Param("idUser") Long idUser, @Param("Survey_id") Long Survey_id, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by) {
        userdoingsurveyRepository.create(idUser, Survey_id, created_at, created_by);
    }

    @Override
    public void edit(@Param("idUser") Long idUser, @Param("Survey_id") Long Survey_id, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id) {
        userdoingsurveyRepository.edit(idUser, Survey_id, updated_at, updated_by, id);
    }

    @Override
    public UserDoingSurvey showUserDoingSurvey(Long Survey_id, Long idUser) {
        return userdoingsurveyRepository.showUserDoingSurvey(Survey_id, idUser);
    }

    @Override
    public Long showIdUserDoingSurvey(@Param("id") Long id, @Param("idUser") Long idUser) {
        return userdoingsurveyRepository.showIdUserDoingSurvey(id, idUser);
    }
}