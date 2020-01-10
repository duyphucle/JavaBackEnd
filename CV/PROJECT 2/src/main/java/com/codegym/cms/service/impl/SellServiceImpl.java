package com.codegym.cms.service.impl;
import com.codegym.cms.model.Sell;
import com.codegym.cms.repository.SellRepository;
import com.codegym.cms.service.SellService;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
public class SellServiceImpl implements SellService {
   @Autowired
   private SellRepository sellRepository;

   @Override
   public Sell findById(Long id){
       return sellRepository.findById(id).get();
}
   @Override
   public Iterable<Sell> findAllByIsDeletedEquals(int isDeleted) {
       return sellRepository.findAllByIsDeletedEquals(isDeleted);
   }
   @Override
    public void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id){
       sellRepository.softDelete(deleted_at, deleted_by, id);
}


    @Override
    public void create(@Param("TenChienDich") String TenChienDich, @Param("PhanTramSale") String PhanTramSale, @Param("NgayBatDau") LocalDate NgayBatDau, @Param("NgayKetThuc") LocalDate NgayKetThuc, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by){
       sellRepository.create(TenChienDich, PhanTramSale, NgayBatDau, NgayKetThuc, created_at, created_by);}
   @Override
    public void edit(@Param("TenChienDich") String TenChienDich, @Param("PhanTramSale") String PhanTramSale, @Param("NgayBatDau") LocalDate NgayBatDau, @Param("NgayKetThuc") LocalDate NgayKetThuc, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long   id){
       sellRepository.edit(TenChienDich, PhanTramSale, NgayBatDau, NgayKetThuc, updated_at, updated_by, id);
 }
}