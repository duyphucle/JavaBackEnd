package com.codegym.cms.service;

import com.codegym.cms.model.Xe;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface XeService {
    Xe findById(Long id);

    Iterable<Xe> findAllByIsDeletedEquals(int isDeleted);

    void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


    void create(@Param("DongXe") String DongXe, @Param("idHang") Long idHang, @Param("HangXe") String HangXe, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


    void edit(@Param("DongXe") String DongXe, @Param("idHang") Long idHang, @Param("HangXe") String HangXe, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id);

    Iterable<String> hangXe();

    Iterable<String> dongXe(@Param("HangXe") String HangXe);

    Iterable<String> hangXe2();
}
