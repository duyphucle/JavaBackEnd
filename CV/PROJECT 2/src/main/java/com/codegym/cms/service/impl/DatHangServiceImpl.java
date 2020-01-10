package com.codegym.cms.service.impl;
import com.codegym.cms.model.User;
import com.codegym.cms.model.DatHang;
import com.codegym.cms.repository.DatHangRepository;
import com.codegym.cms.service.DatHangService;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
public class DatHangServiceImpl implements DatHangService {
   @Autowired
   private DatHangRepository dathangRepository;

   @Override
   public DatHang findById(Long id){
       return dathangRepository.findById(id).get();
}
   @Override
   public Iterable<DatHang> findAllByIsDeletedEquals(int isDeleted) {
       return dathangRepository.findAllByIsDeletedEquals(isDeleted);
   }
   @Override
    public void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id){
       dathangRepository.softDelete(deleted_at, deleted_by, id);
}


    @Override
    public void create(@Param("User_id") Long User_id, @Param("OrderDate") LocalDate OrderDate, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by){
       dathangRepository.create(User_id, OrderDate, created_at, created_by);}
   @Override
    public void edit(@Param("User_id") Long User_id, @Param("OrderDate") LocalDate OrderDate, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long   id){
       dathangRepository.edit(User_id, OrderDate, updated_at, updated_by, id);
 }
}