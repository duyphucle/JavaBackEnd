package com.codegym.cms.repository; 
import com.codegym.cms.model.DapAn;
import com.codegym.cms.model.TraLoi;
import com.codegym.cms.model.TraLoiChiTiet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import javax.transaction.Transactional;
import java.time.LocalDate;
public interface TraLoiChiTietRepository extends PagingAndSortingRepository<TraLoiChiTiet, Long> {

    Iterable<TraLoiChiTiet> findAllByIsDeletedEquals(int isDeleted);

    @Transactional
    @Modifying
    @Query("UPDATE TraLoiChiTiet b SET b.isDeleted = 1, b.deleted_at = :deleted_at, b.deleted_by = :deleted_by WHERE b.id=:id")
    void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO TraLoiChiTiet ( id, idTraLoi, idDapAn, name, luachon, value, innerText, created_at, created_by )" + 
               "VALUES ((SELECT IFNULL((SELECT MAX(id) FROM TraLoiChiTiet C) + 1, 1 )), :idTraLoi, :idDapAn, :name, :luachon, :value, :innerText, :created_at, :created_by);", nativeQuery = true)
    void create(@Param("idTraLoi") Long idTraLoi, @Param("idDapAn") Long idDapAn, @Param("name") String name, @Param("luachon") String luachon, @Param("value") String value, @Param("innerText") String innerText, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


    @Transactional
    @Modifying
    @Query(value = "UPDATE TraLoiChiTiet SET idTraLoi = :idTraLoi, idDapAn = :idDapAn, name = :name, luachon = :luachon, value = :value, innerText = :innerText, updated_at = :updated_at, updated_by = :updated_by WHERE id = :id", nativeQuery = true)
     void edit(@Param("idTraLoi") Long idTraLoi, @Param("idDapAn") Long idDapAn, @Param("name") String name, @Param("luachon") String luachon, @Param("value") String value, @Param("innerText") String innerText, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id);

}
