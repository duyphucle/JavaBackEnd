package com.codegym.cms.service.impl;
import com.codegym.cms.model.Phuong;
import com.codegym.cms.repository.PhuongRepository;
import com.codegym.cms.service.PhuongService;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
public class PhuongServiceImpl implements PhuongService {
   @Autowired
   private PhuongRepository phuongRepository;

   @Override
   public Phuong findById(Long id){
       return phuongRepository.findById(id).get();
}
   @Override
   public Iterable<Phuong> findAllByIsDeletedEquals(int isDeleted) {
       return phuongRepository.findAllByIsDeletedEquals(isDeleted);
   }
   @Override
    public void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id){
       phuongRepository.softDelete(deleted_at, deleted_by, id);
}


    @Override
    public void create(@Param("TenPhuong") String TenPhuong, @Param("Huyen_id") Long Huyen_id, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by){
       phuongRepository.create(TenPhuong, Huyen_id, created_at, created_by);}
   @Override
    public void edit(@Param("TenPhuong") String TenPhuong, @Param("Huyen_id") Long Huyen_id, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long   id){
       phuongRepository.edit(TenPhuong, Huyen_id, updated_at, updated_by, id);
 }
}