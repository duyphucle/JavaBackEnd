package com.codegym.cms.repository;

import com.codegym.cms.model.CauHoi;
import com.codegym.cms.model.UserDoingSurvey;
import com.codegym.cms.model.TraLoi;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;

public interface TraLoiRepository extends PagingAndSortingRepository<TraLoi, Long> {

    Iterable<TraLoi> findAllByIsDeletedEquals(int isDeleted);

    @Transactional
    @Modifying
    @Query("UPDATE TraLoi b SET b.isDeleted = 1, b.deleted_at = :deleted_at, b.deleted_by = :deleted_by WHERE b.id=:id")
    void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO TraLoi ( id, id_userd_doing, id_cauhoi, created_at, created_by )" +
            "VALUES ((SELECT IFNULL((SELECT MAX(id) FROM TraLoi C) + 1, 1 )), :id_userd_doing, :id_cauhoi, :created_at, :created_by);", nativeQuery = true)
    void create(@Param("id_userd_doing") Long id_userd_doing, @Param("id_cauhoi") Long id_cauhoi, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


    @Transactional
    @Modifying
    @Query(value = "UPDATE TraLoi SET id_userd_doing = :id_userd_doing, id_cauhoi = :id_cauhoi, updated_at = :updated_at, updated_by = :updated_by WHERE id = :id", nativeQuery = true)
    void edit(@Param("id_userd_doing") Long id_userd_doing, @Param("id_cauhoi") Long id_cauhoi, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id);


    @Query(value = "SELECT TraLoi.id FROM DapAn\n" +
            "inner join TraLoi on TraLoi.id_cauhoi = DapAn.CauHoi_id Where DapAn.id = :id", nativeQuery = true)
    Long idTraloi(@Param("id") Long id);
}
