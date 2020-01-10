package com.codegym.cms.service;
import com.codegym.cms.model.ThongTin;
import com.codegym.cms.model.User;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
public interface UserService {
   User findById(Long id);
   Iterable<User> findAllByIsDeletedEquals(int isDeleted);
   void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


   void create(@Param("username") String username, @Param("tongdiem") int tongdiem, @Param("diemdarut") int diemdarut, @Param("diemchoduyet") String diemchoduyet, @Param("ThongTin_id") Long ThongTin_id, @Param("email") String email, @Param("password") String password, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


   void edit(@Param("username") String username, @Param("tongdiem") int tongdiem, @Param("diemdarut") int diemdarut, @Param("diemchoduyet") String diemchoduyet, @Param("ThongTin_id") Long ThongTin_id, @Param("email") String email, @Param("password") String password, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id );

   Long idUser(@Param("email") String email);
}
