package com.codegym.cms.repository; 
import com.codegym.cms.model.SanPham;
import com.codegym.cms.model.Sell;
import com.codegym.cms.model.SellTheoSanPham;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import javax.transaction.Transactional;
import java.time.LocalDate;
public interface SellTheoSanPhamRepository extends PagingAndSortingRepository<SellTheoSanPham, Long> {

    Iterable<SellTheoSanPham> findAllByIsDeletedEquals(int isDeleted);

    @Transactional
    @Modifying
    @Query("UPDATE SellTheoSanPham b SET b.isDeleted = 1, b.deleted_at = :deleted_at, b.deleted_by = :deleted_by WHERE b.id=:id")
    void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO SellTheoSanPham ( id, Sell_id, SanPham_id, Gia, created_at, created_by )" + 
               "VALUES ((SELECT IFNULL((SELECT MAX(id) FROM SellTheoSanPham C) + 1, 1 )), :Sell_id, :SanPham_id, :Gia, :created_at, :created_by);", nativeQuery = true)
    void create(@Param("Sell_id") Long Sell_id, @Param("SanPham_id") Long SanPham_id, @Param("Gia") Long Gia, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


    @Transactional
    @Modifying
    @Query(value = "UPDATE SellTheoSanPham SET Sell_id = :Sell_id, SanPham_id = :SanPham_id, Gia = :Gia, updated_at = :updated_at, updated_by = :updated_by WHERE id = :id", nativeQuery = true)
     void edit(@Param("Sell_id") Long Sell_id, @Param("SanPham_id") Long SanPham_id, @Param("Gia") Long Gia, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id);

}
