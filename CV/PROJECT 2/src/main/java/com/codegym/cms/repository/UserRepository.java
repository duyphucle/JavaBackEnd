package com.codegym.cms.repository;

import com.codegym.cms.model.ThongTin;
import com.codegym.cms.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    Iterable<User> findAllByIsDeletedEquals(int isDeleted);

    @Transactional
    @Modifying
    @Query("UPDATE User b SET b.isDeleted = 1, b.deleted_at = :deleted_at, b.deleted_by = :deleted_by WHERE b.id=:id")
    void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO User ( id, username, tongdiem, diemdarut, diemchoduyet, ThongTin_id, email, password, created_at, created_by )" +
            "VALUES ((SELECT IFNULL((SELECT MAX(id) FROM User C) + 1, 1 )), :username, :tongdiem, :diemdarut, :diemchoduyet, :ThongTin_id, :email, :password, :created_at, :created_by);", nativeQuery = true)
    void create(@Param("username") String username, @Param("tongdiem") int tongdiem, @Param("diemdarut") int diemdarut, @Param("diemchoduyet") String diemchoduyet, @Param("ThongTin_id") Long ThongTin_id, @Param("email") String email, @Param("password") String password, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


    @Transactional
    @Modifying
    @Query(value = "UPDATE User SET username = :username, tongdiem = :tongdiem, diemdarut = :diemdarut, diemchoduyet = :diemchoduyet, ThongTin_id = :ThongTin_id, email = :email, password = :password, updated_at = :updated_at, updated_by = :updated_by WHERE id = :id", nativeQuery = true)
    void edit(@Param("username") String username, @Param("tongdiem") int tongdiem, @Param("diemdarut") int diemdarut, @Param("diemchoduyet") String diemchoduyet, @Param("ThongTin_id") Long ThongTin_id, @Param("email") String email, @Param("password") String password, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id);


    @Query(value = "SELECT id FROM User WHERE email = :email", nativeQuery = true)
    Long idUser(@Param("email") String email);
}
