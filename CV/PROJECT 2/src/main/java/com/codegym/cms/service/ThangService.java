package com.codegym.cms.service;
import com.codegym.cms.model.Thang;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
public interface ThangService {
   Thang findById(Long id);
   Iterable<Thang> findAllByIsDeletedEquals(int isDeleted);
   void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


   void create(@Param("name") String name, @Param("class1") String class1, @Param("value") String value, @Param("luachon") String luachon, @Param("innerText") String innerText, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


   void edit(@Param("name") String name, @Param("class1") String class1, @Param("value") String value, @Param("luachon") String luachon, @Param("innerText") String innerText, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id );

}
