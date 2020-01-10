package com.codegym.cms.service;
import com.codegym.cms.model.DapAn;
import com.codegym.cms.model.TraLoi;
import com.codegym.cms.model.TraLoiChiTiet;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
public interface TraLoiChiTietService {
   TraLoiChiTiet findById(Long id);
   Iterable<TraLoiChiTiet> findAllByIsDeletedEquals(int isDeleted);
   void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


   void create(@Param("idTraLoi") Long idTraLoi, @Param("idDapAn") Long idDapAn, @Param("name") String name, @Param("luachon") String luachon, @Param("value") String value, @Param("innerText") String innerText, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


   void edit(@Param("idTraLoi") Long idTraLoi, @Param("idDapAn") Long idDapAn, @Param("name") String name, @Param("luachon") String luachon, @Param("value") String value, @Param("innerText") String innerText, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id );

}
