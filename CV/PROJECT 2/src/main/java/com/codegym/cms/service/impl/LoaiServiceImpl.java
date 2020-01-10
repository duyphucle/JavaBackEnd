package com.codegym.cms.service.impl;
import com.codegym.cms.model.Loai;
import com.codegym.cms.repository.LoaiRepository;
import com.codegym.cms.service.LoaiService;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
public class LoaiServiceImpl implements LoaiService {
   @Autowired
   private LoaiRepository loaiRepository;

   @Override
   public Loai findById(Long id){
       return loaiRepository.findById(id).get();
}
   @Override
   public Iterable<Loai> findAllByIsDeletedEquals(int isDeleted) {
       return loaiRepository.findAllByIsDeletedEquals(isDeleted);
   }
   @Override
    public void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id){
       loaiRepository.softDelete(deleted_at, deleted_by, id);
}


    @Override
    public void create(@Param("TenLoai") String TenLoai, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by){
       loaiRepository.create(TenLoai, created_at, created_by);}
   @Override
    public void edit(@Param("TenLoai") String TenLoai, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long   id){
       loaiRepository.edit(TenLoai, updated_at, updated_by, id);
 }
}