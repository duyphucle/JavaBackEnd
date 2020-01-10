package com.codegym.cms.service;

import com.codegym.cms.model.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CauHoiService {
    CauHoi findById(Long id);

    Iterable<CauHoi> findAllByIsDeletedEquals(int isDeleted);

    void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


    void create(@Param("tencauhoi") String tencauhoi, @Param("site") Long site, @Param("Loai_id") Long Loai_id, @Param("Survey_id") Long Survey_id, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


    void edit(@Param("tencauhoi") String tencauhoi, @Param("site") Long site, @Param("Loai_id") Long Loai_id, @Param("Survey_id") Long Survey_id, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id);

    //   Iterable<CauHoi> show1(@Param("site") Long site);
//    Iterable<CauHoi> show1(@Param("survey") Survey survey, @Param("site") Long site);
    Iterable<CauHoi> show1(@Param("Survey_id") Long Survey_id, @Param("site") Long site);

    Iterable<DapAn> showda(@Param("cauhoi") CauHoi cauHoi);

    Long maxSite();

    List<Long> listIdCHSession();

    List<Long> iDCHByLoai(@Param("Loai_id") Long Loai_id);
}
