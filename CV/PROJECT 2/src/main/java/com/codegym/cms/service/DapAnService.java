package com.codegym.cms.service;
import com.codegym.cms.model.CauHoi;
import com.codegym.cms.model.DapAn;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
public interface DapAnService {
   DapAn findById(Long id);
   Iterable<DapAn> findAllByIsDeletedEquals(int isDeleted);
   void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


   void create(@Param("CauHoi_id") Long CauHoi_id, @Param("name") String name, @Param("class1") String class1, @Param("luachon") String luachon, @Param("value") String value, @Param("innerText") String innerText, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


   void edit(@Param("CauHoi_id") Long CauHoi_id, @Param("name") String name, @Param("class1") String class1, @Param("luachon") String luachon, @Param("value") String value, @Param("innerText") String innerText, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id );

   Iterable<DapAn> findBySite(@Param("site") Long site);
}
