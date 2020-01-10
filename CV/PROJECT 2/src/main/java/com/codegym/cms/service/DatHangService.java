package com.codegym.cms.service;
import com.codegym.cms.model.User;
import com.codegym.cms.model.DatHang;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
public interface DatHangService {
   DatHang findById(Long id);
   Iterable<DatHang> findAllByIsDeletedEquals(int isDeleted);
   void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


   void create(@Param("User_id") Long User_id, @Param("OrderDate") LocalDate OrderDate, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


   void edit(@Param("User_id") Long User_id, @Param("OrderDate") LocalDate OrderDate, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id );

}
