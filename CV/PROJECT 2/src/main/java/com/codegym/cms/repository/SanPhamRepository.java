package com.codegym.cms.repository; 
import com.codegym.cms.model.LoaiSanPham;
import com.codegym.cms.model.NhaCungCap;
import com.codegym.cms.model.SanPham;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import javax.transaction.Transactional;
import java.time.LocalDate;
public interface SanPhamRepository extends PagingAndSortingRepository<SanPham, Long> {

    Iterable<SanPham> findAllByIsDeletedEquals(int isDeleted);

    @Transactional
    @Modifying
    @Query("UPDATE SanPham b SET b.isDeleted = 1, b.deleted_at = :deleted_at, b.deleted_by = :deleted_by WHERE b.id=:id")
    void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO SanPham ( id, TenSanPham, NhaCungCap_id, LoaiSanPham_id, MoTa, created_at, created_by )" + 
               "VALUES ((SELECT IFNULL((SELECT MAX(id) FROM SanPham C) + 1, 1 )), :TenSanPham, :NhaCungCap_id, :LoaiSanPham_id, :MoTa, :created_at, :created_by);", nativeQuery = true)
    void create(@Param("TenSanPham") String TenSanPham, @Param("NhaCungCap_id") Long NhaCungCap_id, @Param("LoaiSanPham_id") Long LoaiSanPham_id, @Param("MoTa") String MoTa, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


    @Transactional
    @Modifying
    @Query(value = "UPDATE SanPham SET TenSanPham = :TenSanPham, NhaCungCap_id = :NhaCungCap_id, LoaiSanPham_id = :LoaiSanPham_id, MoTa = :MoTa, updated_at = :updated_at, updated_by = :updated_by WHERE id = :id", nativeQuery = true)
     void edit(@Param("TenSanPham") String TenSanPham, @Param("NhaCungCap_id") Long NhaCungCap_id, @Param("LoaiSanPham_id") Long LoaiSanPham_id, @Param("MoTa") String MoTa, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id);

}
