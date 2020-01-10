package com.codegym.cms.service;
import com.codegym.cms.model.Loai;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
public interface LoaiService {
   Loai findById(Long id);
   Iterable<Loai> findAllByIsDeletedEquals(int isDeleted);
   void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


   void create(@Param("TenLoai") String TenLoai, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


   void edit(@Param("TenLoai") String TenLoai, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id );

}
