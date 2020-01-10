package com.codegym.cms.service;
import com.codegym.cms.model.ThongTin;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
public interface ThongTinService {
   ThongTin findById(Long id);
   Iterable<ThongTin> findAllByIsDeletedEquals(int isDeleted);
   void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


   void create(@Param("Ten") String Ten, @Param("Tuoi") int Tuoi, @Param("CMND") String CMND, @Param("QueQuan") String QueQuan, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


   void edit(@Param("Ten") String Ten, @Param("Tuoi") int Tuoi, @Param("CMND") String CMND, @Param("QueQuan") String QueQuan, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id );

}
