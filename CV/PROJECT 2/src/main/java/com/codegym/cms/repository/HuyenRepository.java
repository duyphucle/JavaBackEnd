package com.codegym.cms.repository; 
import com.codegym.cms.model.ThanhPho;
import com.codegym.cms.model.Huyen;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import javax.transaction.Transactional;
import java.time.LocalDate;
public interface HuyenRepository extends PagingAndSortingRepository<Huyen, Long> {

    Iterable<Huyen> findAllByIsDeletedEquals(int isDeleted);

    @Transactional
    @Modifying
    @Query("UPDATE Huyen b SET b.isDeleted = 1, b.deleted_at = :deleted_at, b.deleted_by = :deleted_by WHERE b.id=:id")
    void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Huyen ( id, TenHuyen, ThanhPho_id, created_at, created_by )" + 
               "VALUES ((SELECT IFNULL((SELECT MAX(id) FROM Huyen C) + 1, 1 )), :TenHuyen, :ThanhPho_id, :created_at, :created_by);", nativeQuery = true)
    void create(@Param("TenHuyen") String TenHuyen, @Param("ThanhPho_id") Long ThanhPho_id, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


    @Transactional
    @Modifying
    @Query(value = "UPDATE Huyen SET TenHuyen = :TenHuyen, ThanhPho_id = :ThanhPho_id, updated_at = :updated_at, updated_by = :updated_by WHERE id = :id", nativeQuery = true)
     void edit(@Param("TenHuyen") String TenHuyen, @Param("ThanhPho_id") Long ThanhPho_id, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id);

}
