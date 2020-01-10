package com.codegym.cms.repository;

import com.codegym.cms.model.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface CauHoiRepository extends PagingAndSortingRepository<CauHoi, Long> {

    Iterable<CauHoi> findAllByIsDeletedEquals(int isDeleted);

    @Transactional
    @Modifying
    @Query("UPDATE CauHoi b SET b.isDeleted = 1, b.deleted_at = :deleted_at, b.deleted_by = :deleted_by WHERE b.id=:id")
    void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO CauHoi ( id, tencauhoi, site, Loai_id, Survey_id, created_at, created_by )" +
            "VALUES ((SELECT IFNULL((SELECT MAX(id) FROM CauHoi C) + 1, 1 )), :tencauhoi, :site, :Loai_id, :Survey_id, :created_at, :created_by);", nativeQuery = true)
    void create(@Param("tencauhoi") String tencauhoi, @Param("site") Long site, @Param("Loai_id") Long Loai_id, @Param("Survey_id") Long Survey_id, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


    @Transactional
    @Modifying
    @Query(value = "UPDATE CauHoi SET tencauhoi = :tencauhoi, site = :site, Loai_id = :Loai_id, Survey_id = :Survey_id, updated_at = :updated_at, updated_by = :updated_by WHERE id = :id", nativeQuery = true)
    void edit(@Param("tencauhoi") String tencauhoi, @Param("site") Long site, @Param("Loai_id") Long Loai_id, @Param("Survey_id") Long Survey_id, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id);


    @Query("SELECT d FROM DapAn d where d.cauhoi = :cauhoi")
    Iterable<DapAn> showda(@Param("cauhoi") CauHoi cauHoi);


//    @Query(value = "SELECT * FROM DapAn  where CauHoi_id = :CauHoi_id", nativeQuery = true)
//    Iterable<DapAn> showda2(@Param("CauHoi_id") Long CauHoi_id);
//
//    @Query(value = "select TenLoai from Loai inner join CauHoi on CauHoi.Loai_id = Loai.id where CauHoi.id =:id", nativeQuery = true)
//    String loai(@Param("id") Long id);

    //a.phuc noi cho ni show ra loai, dap an, cau hoi
//    @Query("select c from CauHoi c where c.site = :site")
//    Iterable<CauHoi> show1(@Param("site") Long site);

//    @Query("select c from CauHoi c where c.survey = :survey and c.site = :site")
//    Iterable<CauHoi> show1(@Param("survey") Survey survey, @Param("site") Long site);

    @Query(value = "select * from CauHoi where Survey_id = :Survey_id and site = :site", nativeQuery = true)
    Iterable<CauHoi> show1(@Param("Survey_id") Long Survey_id, @Param("site") Long site);

//    @Query(value = "select DapAn.* from DapAn inner join CauHoi on CauHoi.id = DapAn.CauHoi_id where CauHoi.site =?1",nativeQuery = true)
//    Iterable<DapAn> findBySite(@Param("site") Long site);

//    @Query(value = "select DapAn.* from DapAn inner join CauHoi where CauHoi.site = :site", nativeQuery = true)
//    Iterable<DapAn> findBySite(@Param("site") Long site);
//
//    @Query("SELECT tp FROM ThanhPho tp")
//    Iterable<ThanhPho> showtp();

    @Query(value = "SELECT MAX(site) FROM CauHoi", nativeQuery = true)
    Long maxSite();

    @Query(value = "select CauHoi.id from CauHoi inner join Loai on CauHoi.Loai_id = Loai.id where Loai.TenLoai = \"session\"", nativeQuery = true)
    List<Long> listIdCHSession();

    @Query(value = "SELECT id FROM CauHoi WHERE CauHoi.Loai_id = :Loai_id", nativeQuery = true)
    List<Long> iDCHByLoai(@Param("Loai_id") Long Loai_id);
}
