package com.codegym.cms.repository;

import com.codegym.cms.model.Survey;
import com.codegym.cms.model.User;
import com.codegym.cms.model.UserDoingSurvey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;

public interface UserDoingSurveyRepository extends PagingAndSortingRepository<UserDoingSurvey, Long> {

    Iterable<UserDoingSurvey> findAllByIsDeletedEquals(int isDeleted);

    @Transactional
    @Modifying
    @Query("UPDATE UserDoingSurvey b SET b.isDeleted = 1, b.deleted_at = :deleted_at, b.deleted_by = :deleted_by WHERE b.id=:id")
    void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO UserDoingSurvey ( id, idUser, Survey_id, created_at, created_by )" +
            "VALUES ((SELECT IFNULL((SELECT MAX(id) FROM UserDoingSurvey C) + 1, 1 )), :idUser, :Survey_id, :created_at, :created_by);", nativeQuery = true)
    void create(@Param("idUser") Long idUser, @Param("Survey_id") Long Survey_id, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


    @Transactional
    @Modifying
    @Query(value = "UPDATE UserDoingSurvey SET idUser = :idUser, Survey_id = :Survey_id, updated_at = :updated_at, updated_by = :updated_by WHERE id = :id", nativeQuery = true)
    void edit(@Param("idUser") Long idUser, @Param("Survey_id") Long Survey_id, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id);

    @Query(value = "SELECT * FROM UserDoingSurvey WHERE Survey_id = :Survey_id  AND idUser = :idUser", nativeQuery = true)
    UserDoingSurvey showUserDoingSurvey(@Param("Survey_id") Long Survey_id, @Param("idUser") Long idUser);

    @Query(value = "select UserDoingSurvey.id from CauHoi\n" +
            "inner join UserDoingSurvey on UserDoingSurvey.Survey_id = CauHoi.Survey_id Where CauHoi.id = :id AND idUser = :idUser", nativeQuery = true)
    Long showIdUserDoingSurvey(@Param("id") Long id, @Param("idUser") Long idUser);
}
