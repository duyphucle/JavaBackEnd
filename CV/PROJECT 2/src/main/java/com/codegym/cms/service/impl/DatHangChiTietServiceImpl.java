package com.codegym.cms.service.impl;
import com.codegym.cms.model.DatHang;
import com.codegym.cms.model.SellTheoSanPham;
import com.codegym.cms.model.DatHangChiTiet;
import com.codegym.cms.repository.DatHangChiTietRepository;
import com.codegym.cms.service.DatHangChiTietService;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
public class DatHangChiTietServiceImpl implements DatHangChiTietService {
   @Autowired
   private DatHangChiTietRepository dathangchitietRepository;

   @Override
   public DatHangChiTiet findById(Long id){
       return dathangchitietRepository.findById(id).get();
}
   @Override
   public Iterable<DatHangChiTiet> findAllByIsDeletedEquals(int isDeleted) {
       return dathangchitietRepository.findAllByIsDeletedEquals(isDeleted);
   }
   @Override
    public void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id){
       dathangchitietRepository.softDelete(deleted_at, deleted_by, id);
}


    @Override
    public void create(@Param("Order_id") Long Order_id, @Param("SanPham_id") Long SanPham_id, @Param("SoLuong") int SoLuong, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by){
       dathangchitietRepository.create(Order_id, SanPham_id, SoLuong, created_at, created_by);}
   @Override
    public void edit(@Param("Order_id") Long Order_id, @Param("SanPham_id") Long SanPham_id, @Param("SoLuong") int SoLuong, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long   id){
       dathangchitietRepository.edit(Order_id, SanPham_id, SoLuong, updated_at, updated_by, id);
 }
}