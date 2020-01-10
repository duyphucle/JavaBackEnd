package com.codegym.cms.service.impl;
import com.codegym.cms.model.SanPham;
import com.codegym.cms.model.NhapKho;
import com.codegym.cms.repository.NhapKhoRepository;
import com.codegym.cms.service.NhapKhoService;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
public class NhapKhoServiceImpl implements NhapKhoService {
   @Autowired
   private NhapKhoRepository nhapkhoRepository;

   @Override
   public NhapKho findById(Long id){
       return nhapkhoRepository.findById(id).get();
}
   @Override
   public Iterable<NhapKho> findAllByIsDeletedEquals(int isDeleted) {
       return nhapkhoRepository.findAllByIsDeletedEquals(isDeleted);
   }
   @Override
    public void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id){
       nhapkhoRepository.softDelete(deleted_at, deleted_by, id);
}


    @Override
    public void create(@Param("SanPham_id") Long SanPham_id, @Param("Gia") Long Gia, @Param("NgayNhapKho") LocalDate NgayNhapKho, @Param("SoLuong") int SoLuong, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by){
       nhapkhoRepository.create(SanPham_id, Gia, NgayNhapKho, SoLuong, created_at, created_by);}
   @Override
    public void edit(@Param("SanPham_id") Long SanPham_id, @Param("Gia") Long Gia, @Param("NgayNhapKho") LocalDate NgayNhapKho, @Param("SoLuong") int SoLuong, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long   id){
       nhapkhoRepository.edit(SanPham_id, Gia, NgayNhapKho, SoLuong, updated_at, updated_by, id);
 }
}