package com.codegym.cms.service.impl;

import com.codegym.cms.model.*;
import com.codegym.cms.repository.CauHoiRepository;
import com.codegym.cms.service.CauHoiService;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class CauHoiServiceImpl implements CauHoiService {
    @Autowired
    private CauHoiRepository cauhoiRepository;

    @Override
    public CauHoi findById(Long id) {
        return cauhoiRepository.findById(id).get();
    }

    @Override
    public Iterable<CauHoi> findAllByIsDeletedEquals(int isDeleted) {
        return cauhoiRepository.findAllByIsDeletedEquals(isDeleted);
    }

    @Override
    public void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id) {
        cauhoiRepository.softDelete(deleted_at, deleted_by, id);
    }


    @Override
    public void create(@Param("tencauhoi") String tencauhoi, @Param("site") Long site, @Param("Loai_id") Long Loai_id, @Param("Survey_id") Long Survey_id, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by) {
        cauhoiRepository.create(tencauhoi, site, Loai_id, Survey_id, created_at, created_by);
    }

    @Override
    public void edit(@Param("tencauhoi") String tencauhoi, @Param("site") Long site, @Param("Loai_id") Long Loai_id, @Param("Survey_id") Long Survey_id, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id) {
        cauhoiRepository.edit(tencauhoi, site, Loai_id, Survey_id, updated_at, updated_by, id);
    }

    @Override
    public Iterable<CauHoi> show1(Long Survey_id, Long site) {
        return cauhoiRepository.show1(Survey_id, site);
    }

//    @Override
//    public Iterable<CauHoi> show1(Survey survey, Long site) {
//        return cauhoiRepository.show1(survey, site);
//    }


//    @Override
//    public Iterable<CauHoi> show1(Long site) {
//        return cauhoiRepository.show1(site);
//    }

    @Override
    public Iterable<DapAn> showda(CauHoi cauHoi) {
        return cauhoiRepository.showda(cauHoi);
    }

    @Override
    public Long maxSite() {
        return cauhoiRepository.maxSite();
    }

    @Override
    public List<Long> listIdCHSession() {
        return cauhoiRepository.listIdCHSession();
    }

    @Override
    public List<Long> iDCHByLoai(Long Loai_id) {
        return cauhoiRepository.iDCHByLoai(Loai_id);
    }

}