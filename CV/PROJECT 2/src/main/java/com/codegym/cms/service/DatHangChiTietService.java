package com.codegym.cms.service;
import com.codegym.cms.model.DatHang;
import com.codegym.cms.model.SellTheoSanPham;
import com.codegym.cms.model.DatHangChiTiet;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
public interface DatHangChiTietService {
   DatHangChiTiet findById(Long id);
   Iterable<DatHangChiTiet> findAllByIsDeletedEquals(int isDeleted);
   void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


   void create(@Param("Order_id") Long Order_id, @Param("SanPham_id") Long SanPham_id, @Param("SoLuong") int SoLuong, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


   void edit(@Param("Order_id") Long Order_id, @Param("SanPham_id") Long SanPham_id, @Param("SoLuong") int SoLuong, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id );

}
