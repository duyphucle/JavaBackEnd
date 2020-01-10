package com.codegym.cms.service;
import com.codegym.cms.model.NhaCungCap;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
public interface NhaCungCapService {
   NhaCungCap findById(Long id);
   Iterable<NhaCungCap> findAllByIsDeletedEquals(int isDeleted);
   void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


   void create(@Param("TenNhaCungCap") String TenNhaCungCap, @Param("DiaChi") String DiaChi, @Param("ThanhPho") String ThanhPho, @Param("QuocGia") String QuocGia, @Param("MaBuuDien") String MaBuuDien, @Param("SDT") String SDT, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


   void edit(@Param("TenNhaCungCap") String TenNhaCungCap, @Param("DiaChi") String DiaChi, @Param("ThanhPho") String ThanhPho, @Param("QuocGia") String QuocGia, @Param("MaBuuDien") String MaBuuDien, @Param("SDT") String SDT, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id );

}
