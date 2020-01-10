package com.codegym.cms.repository; 
import com.codegym.cms.model.SanPham;
import com.codegym.cms.model.NhapKho;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import javax.transaction.Transactional;
import java.time.LocalDate;
public interface NhapKhoRepository extends PagingAndSortingRepository<NhapKho, Long> {

    Iterable<NhapKho> findAllByIsDeletedEquals(int isDeleted);

    @Transactional
    @Modifying
    @Query("UPDATE NhapKho b SET b.isDeleted = 1, b.deleted_at = :deleted_at, b.deleted_by = :deleted_by WHERE b.id=:id")
    void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO NhapKho ( id, SanPham_id, Gia, NgayNhapKho, SoLuong, created_at, created_by )" + 
               "VALUES ((SELECT IFNULL((SELECT MAX(id) FROM NhapKho C) + 1, 1 )), :SanPham_id, :Gia, :NgayNhapKho, :SoLuong, :created_at, :created_by);", nativeQuery = true)
    void create(@Param("SanPham_id") Long SanPham_id, @Param("Gia") Long Gia, @Param("NgayNhapKho") LocalDate NgayNhapKho, @Param("SoLuong") int SoLuong, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


    @Transactional
    @Modifying
    @Query(value = "UPDATE NhapKho SET SanPham_id = :SanPham_id, Gia = :Gia, NgayNhapKho = :NgayNhapKho, SoLuong = :SoLuong, updated_at = :updated_at, updated_by = :updated_by WHERE id = :id", nativeQuery = true)
     void edit(@Param("SanPham_id") Long SanPham_id, @Param("Gia") Long Gia, @Param("NgayNhapKho") LocalDate NgayNhapKho, @Param("SoLuong") int SoLuong, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id);

}
