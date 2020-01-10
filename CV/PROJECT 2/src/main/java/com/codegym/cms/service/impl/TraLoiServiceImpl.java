package com.codegym.cms.service.impl;

import com.codegym.cms.model.CauHoi;
import com.codegym.cms.model.UserDoingSurvey;
import com.codegym.cms.model.TraLoi;
import com.codegym.cms.repository.TraLoiRepository;
import com.codegym.cms.service.TraLoiService;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class TraLoiServiceImpl implements TraLoiService {
    @Autowired
    private TraLoiRepository traloiRepository;

    @Override
    public TraLoi findById(Long id) {
        return traloiRepository.findById(id).get();
    }

    @Override
    public Iterable<TraLoi> findAllByIsDeletedEquals(int isDeleted) {
        return traloiRepository.findAllByIsDeletedEquals(isDeleted);
    }

    @Override
    public void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id) {
        traloiRepository.softDelete(deleted_at, deleted_by, id);
    }


    @Override
    public void create(@Param("id_userd_doing") Long id_userd_doing, @Param("id_cauhoi") Long id_cauhoi, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by) {
        traloiRepository.create(id_userd_doing, id_cauhoi, created_at, created_by);
    }

    @Override
    public void edit(@Param("id_userd_doing") Long id_userd_doing, @Param("id_cauhoi") Long id_cauhoi, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id) {
        traloiRepository.edit(id_userd_doing, id_cauhoi, updated_at, updated_by, id);
    }

    @Override
    public Long idTraloi(Long id) {
        return traloiRepository.idTraloi(id);
    }

}