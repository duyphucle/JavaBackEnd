package com.codegym.cms.service;
import com.codegym.cms.model.Survey;
import com.codegym.cms.model.User;
import com.codegym.cms.model.UserDoingSurvey;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
public interface UserDoingSurveyService {
   UserDoingSurvey findById(Long id);
   Iterable<UserDoingSurvey> findAllByIsDeletedEquals(int isDeleted);
   void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


   void create(@Param("idUser") Long idUser, @Param("Survey_id") Long Survey_id, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


   void edit(@Param("idUser") Long idUser, @Param("Survey_id") Long Survey_id, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id );

   UserDoingSurvey showUserDoingSurvey(@Param("Survey_id") Long Survey_id, @Param("idUser") Long idUser);

   Long showIdUserDoingSurvey(@Param("id") Long id, @Param("idUser") Long idUser);
}
