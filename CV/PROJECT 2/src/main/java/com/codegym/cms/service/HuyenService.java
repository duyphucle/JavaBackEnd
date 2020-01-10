package com.codegym.cms.service;
import com.codegym.cms.model.ThanhPho;
import com.codegym.cms.model.Huyen;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
public interface HuyenService {
   Huyen findById(Long id);
   Iterable<Huyen> findAllByIsDeletedEquals(int isDeleted);
   void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


   void create(@Param("TenHuyen") String TenHuyen, @Param("ThanhPho_id") Long ThanhPho_id, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


   void edit(@Param("TenHuyen") String TenHuyen, @Param("ThanhPho_id") Long ThanhPho_id, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id );

}
