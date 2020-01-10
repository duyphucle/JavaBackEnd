package com.codegym.cms.service.impl;
import com.codegym.cms.model.LoaiSanPham;
import com.codegym.cms.model.NhaCungCap;
import com.codegym.cms.model.SanPham;
import com.codegym.cms.repository.SanPhamRepository;
import com.codegym.cms.service.SanPhamService;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
public class SanPhamServiceImpl implements SanPhamService {
   @Autowired
   private SanPhamRepository sanphamRepository;

   @Override
   public SanPham findById(Long id){
       return sanphamRepository.findById(id).get();
}
   @Override
   public Iterable<SanPham> findAllByIsDeletedEquals(int isDeleted) {
       return sanphamRepository.findAllByIsDeletedEquals(isDeleted);
   }
   @Override
    public void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id){
       sanphamRepository.softDelete(deleted_at, deleted_by, id);
}


    @Override
    public void create(@Param("TenSanPham") String TenSanPham, @Param("NhaCungCap_id") Long NhaCungCap_id, @Param("LoaiSanPham_id") Long LoaiSanPham_id, @Param("MoTa") String MoTa, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by){
       sanphamRepository.create(TenSanPham, NhaCungCap_id, LoaiSanPham_id, MoTa, created_at, created_by);}
   @Override
    public void edit(@Param("TenSanPham") String TenSanPham, @Param("NhaCungCap_id") Long NhaCungCap_id, @Param("LoaiSanPham_id") Long LoaiSanPham_id, @Param("MoTa") String MoTa, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long   id){
       sanphamRepository.edit(TenSanPham, NhaCungCap_id, LoaiSanPham_id, MoTa, updated_at, updated_by, id);
 }
}