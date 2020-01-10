package com.codegym.cms.service;
import com.codegym.cms.model.CauHoi;
import com.codegym.cms.model.UserDoingSurvey;
import com.codegym.cms.model.TraLoi;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
public interface TraLoiService {
   TraLoi findById(Long id);
   Iterable<TraLoi> findAllByIsDeletedEquals(int isDeleted);
   void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


   void create(@Param("id_userd_doing") Long id_userd_doing, @Param("id_cauhoi") Long id_cauhoi, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


   void edit(@Param("id_userd_doing") Long id_userd_doing, @Param("id_cauhoi") Long id_cauhoi, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id );

   Long idTraloi(@Param("id") Long id);
}
