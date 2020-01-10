package com.codegym.cms.service.impl;
import com.codegym.cms.model.Survey;
import com.codegym.cms.repository.SurveyRepository;
import com.codegym.cms.service.SurveyService;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
public class SurveyServiceImpl implements SurveyService {
   @Autowired
   private SurveyRepository surveyRepository;

   @Override
   public Survey findById(Long id){
       return surveyRepository.findById(id).get();
}
   @Override
   public Iterable<Survey> findAllByIsDeletedEquals(int isDeleted) {
       return surveyRepository.findAllByIsDeletedEquals(isDeleted);
   }
   @Override
    public void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id){
       surveyRepository.softDelete(deleted_at, deleted_by, id);
}


    @Override
    public void create(@Param("tenkhaosat") String tenkhaosat, @Param("diem") int diem, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by){
       surveyRepository.create(tenkhaosat, diem, created_at, created_by);}
   @Override
    public void edit(@Param("tenkhaosat") String tenkhaosat, @Param("diem") int diem, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long   id){
       surveyRepository.edit(tenkhaosat, diem, updated_at, updated_by, id);
 }
}