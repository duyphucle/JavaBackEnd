package com.codegym.cms.service.impl;
import com.codegym.cms.model.DapAn;
import com.codegym.cms.model.TraLoi;
import com.codegym.cms.model.TraLoiChiTiet;
import com.codegym.cms.repository.TraLoiChiTietRepository;
import com.codegym.cms.service.TraLoiChiTietService;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
public class TraLoiChiTietServiceImpl implements TraLoiChiTietService {
   @Autowired
   private TraLoiChiTietRepository traloichitietRepository;

   @Override
   public TraLoiChiTiet findById(Long id){
       return traloichitietRepository.findById(id).get();
}
   @Override
   public Iterable<TraLoiChiTiet> findAllByIsDeletedEquals(int isDeleted) {
       return traloichitietRepository.findAllByIsDeletedEquals(isDeleted);
   }
   @Override
    public void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id){
       traloichitietRepository.softDelete(deleted_at, deleted_by, id);
}


    @Override
    public void create(@Param("idTraLoi") Long idTraLoi, @Param("idDapAn") Long idDapAn, @Param("name") String name, @Param("luachon") String luachon, @Param("value") String value, @Param("innerText") String innerText, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by){
       traloichitietRepository.create(idTraLoi, idDapAn, name, luachon, value, innerText, created_at, created_by);}
   @Override
    public void edit(@Param("idTraLoi") Long idTraLoi, @Param("idDapAn") Long idDapAn, @Param("name") String name, @Param("luachon") String luachon, @Param("value") String value, @Param("innerText") String innerText, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long   id){
       traloichitietRepository.edit(idTraLoi, idDapAn, name, luachon, value, innerText, updated_at, updated_by, id);
 }
}