package com.codegym.cms.service.impl;
import com.codegym.cms.model.Thang;
import com.codegym.cms.repository.ThangRepository;
import com.codegym.cms.service.ThangService;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
public class ThangServiceImpl implements ThangService {
   @Autowired
   private ThangRepository thangRepository;

   @Override
   public Thang findById(Long id){
       return thangRepository.findById(id).get();
}
   @Override
   public Iterable<Thang> findAllByIsDeletedEquals(int isDeleted) {
       return thangRepository.findAllByIsDeletedEquals(isDeleted);
   }
   @Override
    public void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id){
       thangRepository.softDelete(deleted_at, deleted_by, id);
}


    @Override
    public void create(@Param("name") String name, @Param("class1") String class1, @Param("value") String value, @Param("luachon") String luachon, @Param("innerText") String innerText, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by){
       thangRepository.create(name, class1, value, luachon, innerText, created_at, created_by);}
   @Override
    public void edit(@Param("name") String name, @Param("class1") String class1, @Param("value") String value, @Param("luachon") String luachon, @Param("innerText") String innerText, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long   id){
       thangRepository.edit(name, class1, value, luachon, innerText, updated_at, updated_by, id);
 }
}