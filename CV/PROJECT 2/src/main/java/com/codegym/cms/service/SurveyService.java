package com.codegym.cms.service;
import com.codegym.cms.model.Survey;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
public interface SurveyService {
   Survey findById(Long id);
   Iterable<Survey> findAllByIsDeletedEquals(int isDeleted);
   void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


   void create(@Param("tenkhaosat") String tenkhaosat, @Param("diem") int diem, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


   void edit(@Param("tenkhaosat") String tenkhaosat, @Param("diem") int diem, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id );

}
