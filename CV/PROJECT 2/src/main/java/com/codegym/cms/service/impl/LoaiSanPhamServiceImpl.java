package com.codegym.cms.service.impl;
import com.codegym.cms.model.LoaiSanPham;
import com.codegym.cms.repository.LoaiSanPhamRepository;
import com.codegym.cms.service.LoaiSanPhamService;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
public class LoaiSanPhamServiceImpl implements LoaiSanPhamService {
   @Autowired
   private LoaiSanPhamRepository loaisanphamRepository;

   @Override
   public LoaiSanPham findById(Long id){
       return loaisanphamRepository.findById(id).get();
}
   @Override
   public Iterable<LoaiSanPham> findAllByIsDeletedEquals(int isDeleted) {
       return loaisanphamRepository.findAllByIsDeletedEquals(isDeleted);
   }
   @Override
    public void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id){
       loaisanphamRepository.softDelete(deleted_at, deleted_by, id);
}


    @Override
    public void create(@Param("TenTheLoai") String TenTheLoai, @Param("MoTa") String MoTa, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by){
       loaisanphamRepository.create(TenTheLoai, MoTa, created_at, created_by);}
   @Override
    public void edit(@Param("TenTheLoai") String TenTheLoai, @Param("MoTa") String MoTa, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long   id){
       loaisanphamRepository.edit(TenTheLoai, MoTa, updated_at, updated_by, id);
 }
}