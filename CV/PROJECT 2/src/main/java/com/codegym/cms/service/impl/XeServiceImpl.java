package com.codegym.cms.service.impl;

import com.codegym.cms.model.Xe;
import com.codegym.cms.repository.XeRepository;
import com.codegym.cms.service.XeService;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class XeServiceImpl implements XeService {
    @Autowired
    private XeRepository xeRepository;

    @Override
    public Xe findById(Long id) {
        return xeRepository.findById(id).get();
    }

    @Override
    public Iterable<Xe> findAllByIsDeletedEquals(int isDeleted) {
        return xeRepository.findAllByIsDeletedEquals(isDeleted);
    }

    @Override
    public void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id) {
        xeRepository.softDelete(deleted_at, deleted_by, id);
    }


    @Override
    public void create(@Param("DongXe") String DongXe, @Param("idHang") Long idHang, @Param("HangXe") String HangXe, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by) {
        xeRepository.create(DongXe, idHang, HangXe, created_at, created_by);
    }

    @Override
    public void edit(@Param("DongXe") String DongXe, @Param("idHang") Long idHang, @Param("HangXe") String HangXe, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id) {
        xeRepository.edit(DongXe, idHang, HangXe, updated_at, updated_by, id);
    }

    @Override
    public Iterable<String> hangXe() {
        return xeRepository.hangXe();
    }

    @Override
    public Iterable<String> dongXe(String HangXe) {
        return xeRepository.dongXe(HangXe);
    }

    @Override
    public Iterable<String> hangXe2() {
        return xeRepository.hangXe2();
    }
}