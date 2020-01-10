package com.codegym.cms.service.impl;
import com.codegym.cms.model.ThanhPho;
import com.codegym.cms.model.Huyen;
import com.codegym.cms.repository.HuyenRepository;
import com.codegym.cms.service.HuyenService;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
public class HuyenServiceImpl implements HuyenService {
   @Autowired
   private HuyenRepository huyenRepository;

   @Override
   public Huyen findById(Long id){
       return huyenRepository.findById(id).get();
}
   @Override
   public Iterable<Huyen> findAllByIsDeletedEquals(int isDeleted) {
       return huyenRepository.findAllByIsDeletedEquals(isDeleted);
   }
   @Override
    public void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id){
       huyenRepository.softDelete(deleted_at, deleted_by, id);
}


    @Override
    public void create(@Param("TenHuyen") String TenHuyen, @Param("ThanhPho_id") Long ThanhPho_id, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by){
       huyenRepository.create(TenHuyen, ThanhPho_id, created_at, created_by);}
   @Override
    public void edit(@Param("TenHuyen") String TenHuyen, @Param("ThanhPho_id") Long ThanhPho_id, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long   id){
       huyenRepository.edit(TenHuyen, ThanhPho_id, updated_at, updated_by, id);
 }
}