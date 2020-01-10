package com.codegym.cms.repository; 
import com.codegym.cms.model.DatHang;
import com.codegym.cms.model.SellTheoSanPham;
import com.codegym.cms.model.DatHangChiTiet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import javax.transaction.Transactional;
import java.time.LocalDate;
public interface DatHangChiTietRepository extends PagingAndSortingRepository<DatHangChiTiet, Long> {

    Iterable<DatHangChiTiet> findAllByIsDeletedEquals(int isDeleted);

    @Transactional
    @Modifying
    @Query("UPDATE DatHangChiTiet b SET b.isDeleted = 1, b.deleted_at = :deleted_at, b.deleted_by = :deleted_by WHERE b.id=:id")
    void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO DatHangChiTiet ( id, Order_id, SanPham_id, SoLuong, created_at, created_by )" + 
               "VALUES ((SELECT IFNULL((SELECT MAX(id) FROM DatHangChiTiet C) + 1, 1 )), :Order_id, :SanPham_id, :SoLuong, :created_at, :created_by);", nativeQuery = true)
    void create(@Param("Order_id") Long Order_id, @Param("SanPham_id") Long SanPham_id, @Param("SoLuong") int SoLuong, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


    @Transactional
    @Modifying
    @Query(value = "UPDATE DatHangChiTiet SET Order_id = :Order_id, SanPham_id = :SanPham_id, SoLuong = :SoLuong, updated_at = :updated_at, updated_by = :updated_by WHERE id = :id", nativeQuery = true)
     void edit(@Param("Order_id") Long Order_id, @Param("SanPham_id") Long SanPham_id, @Param("SoLuong") int SoLuong, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id);

}
