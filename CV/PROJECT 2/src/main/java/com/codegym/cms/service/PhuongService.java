package com.codegym.cms.service;
import com.codegym.cms.model.Phuong;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
public interface PhuongService {
   Phuong findById(Long id);
   Iterable<Phuong> findAllByIsDeletedEquals(int isDeleted);
   void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


   void create(@Param("TenPhuong") String TenPhuong, @Param("Huyen_id") Long Huyen_id, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


   void edit(@Param("TenPhuong") String TenPhuong, @Param("Huyen_id") Long Huyen_id, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id );

}
