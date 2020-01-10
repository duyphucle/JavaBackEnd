package com.codegym.cms.service.impl;
import com.codegym.cms.model.ThongTin;
import com.codegym.cms.repository.ThongTinRepository;
import com.codegym.cms.service.ThongTinService;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
public class ThongTinServiceImpl implements ThongTinService {
   @Autowired
   private ThongTinRepository thongtinRepository;

   @Override
   public ThongTin findById(Long id){
       return thongtinRepository.findById(id).get();
}
   @Override
   public Iterable<ThongTin> findAllByIsDeletedEquals(int isDeleted) {
       return thongtinRepository.findAllByIsDeletedEquals(isDeleted);
   }
   @Override
    public void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id){
       thongtinRepository.softDelete(deleted_at, deleted_by, id);
}


    @Override
    public void create(@Param("Ten") String Ten, @Param("Tuoi") int Tuoi, @Param("CMND") String CMND, @Param("QueQuan") String QueQuan, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by){
       thongtinRepository.create(Ten, Tuoi, CMND, QueQuan, created_at, created_by);}
   @Override
    public void edit(@Param("Ten") String Ten, @Param("Tuoi") int Tuoi, @Param("CMND") String CMND, @Param("QueQuan") String QueQuan, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long   id){
       thongtinRepository.edit(Ten, Tuoi, CMND, QueQuan, updated_at, updated_by, id);
 }
}