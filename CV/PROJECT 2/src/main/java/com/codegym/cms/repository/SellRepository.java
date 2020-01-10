package com.codegym.cms.repository; 
import com.codegym.cms.model.Sell;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import javax.transaction.Transactional;
import java.time.LocalDate;
public interface SellRepository extends PagingAndSortingRepository<Sell, Long> {

    Iterable<Sell> findAllByIsDeletedEquals(int isDeleted);

    @Transactional
    @Modifying
    @Query("UPDATE Sell b SET b.isDeleted = 1, b.deleted_at = :deleted_at, b.deleted_by = :deleted_by WHERE b.id=:id")
    void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Sell ( id, TenChienDich, PhanTramSale, NgayBatDau, NgayKetThuc, created_at, created_by )" + 
               "VALUES ((SELECT IFNULL((SELECT MAX(id) FROM Sell C) + 1, 1 )), :TenChienDich, :PhanTramSale, :NgayBatDau, :NgayKetThuc, :created_at, :created_by);", nativeQuery = true)
    void create(@Param("TenChienDich") String TenChienDich, @Param("PhanTramSale") String PhanTramSale, @Param("NgayBatDau") LocalDate NgayBatDau, @Param("NgayKetThuc") LocalDate NgayKetThuc, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


    @Transactional
    @Modifying
    @Query(value = "UPDATE Sell SET TenChienDich = :TenChienDich, PhanTramSale = :PhanTramSale, NgayBatDau = :NgayBatDau, NgayKetThuc = :NgayKetThuc, updated_at = :updated_at, updated_by = :updated_by WHERE id = :id", nativeQuery = true)
     void edit(@Param("TenChienDich") String TenChienDich, @Param("PhanTramSale") String PhanTramSale, @Param("NgayBatDau") LocalDate NgayBatDau, @Param("NgayKetThuc") LocalDate NgayKetThuc, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id);

}
