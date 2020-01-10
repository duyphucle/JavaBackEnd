package com.codegym.cms.repository; 
import com.codegym.cms.model.Loai;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import javax.transaction.Transactional;
import java.time.LocalDate;
public interface LoaiRepository extends PagingAndSortingRepository<Loai, Long> {

    Iterable<Loai> findAllByIsDeletedEquals(int isDeleted);

    @Transactional
    @Modifying
    @Query("UPDATE Loai b SET b.isDeleted = 1, b.deleted_at = :deleted_at, b.deleted_by = :deleted_by WHERE b.id=:id")
    void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Loai ( id, TenLoai, created_at, created_by )" + 
               "VALUES ((SELECT IFNULL((SELECT MAX(id) FROM Loai C) + 1, 1 )), :TenLoai, :created_at, :created_by);", nativeQuery = true)
    void create(@Param("TenLoai") String TenLoai, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


    @Transactional
    @Modifying
    @Query(value = "UPDATE Loai SET TenLoai = :TenLoai, updated_at = :updated_at, updated_by = :updated_by WHERE id = :id", nativeQuery = true)
     void edit(@Param("TenLoai") String TenLoai, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id);

}
