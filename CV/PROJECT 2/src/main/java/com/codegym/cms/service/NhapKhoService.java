package com.codegym.cms.service;
import com.codegym.cms.model.SanPham;
import com.codegym.cms.model.NhapKho;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
public interface NhapKhoService {
   NhapKho findById(Long id);
   Iterable<NhapKho> findAllByIsDeletedEquals(int isDeleted);
   void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


   void create(@Param("SanPham_id") Long SanPham_id, @Param("Gia") Long Gia, @Param("NgayNhapKho") LocalDate NgayNhapKho, @Param("SoLuong") int SoLuong, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


   void edit(@Param("SanPham_id") Long SanPham_id, @Param("Gia") Long Gia, @Param("NgayNhapKho") LocalDate NgayNhapKho, @Param("SoLuong") int SoLuong, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id );

}
