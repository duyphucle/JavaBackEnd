package com.codegym.cms.service.impl;
import com.codegym.cms.model.ThanhPho;
import com.codegym.cms.repository.ThanhPhoRepository;
import com.codegym.cms.service.ThanhPhoService;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
public class ThanhPhoServiceImpl implements ThanhPhoService {
   @Autowired
   private ThanhPhoRepository thanhphoRepository;

   @Override
   public ThanhPho findById(Long id){
       return thanhphoRepository.findById(id).get();
}
   @Override
   public Iterable<ThanhPho> findAllByIsDeletedEquals(int isDeleted) {
       return thanhphoRepository.findAllByIsDeletedEquals(isDeleted);
   }
   @Override
    public void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id){
       thanhphoRepository.softDelete(deleted_at, deleted_by, id);
}


    @Override
    public void create(@Param("ThanhPho") String ThanhPho, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by){
       thanhphoRepository.create(ThanhPho, created_at, created_by);}
   @Override
    public void edit(@Param("ThanhPho") String ThanhPho, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long   id){
       thanhphoRepository.edit(ThanhPho, updated_at, updated_by, id);
 }
}