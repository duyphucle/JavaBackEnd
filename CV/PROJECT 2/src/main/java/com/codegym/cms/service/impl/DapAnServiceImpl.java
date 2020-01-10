package com.codegym.cms.service.impl;
import com.codegym.cms.model.CauHoi;
import com.codegym.cms.model.DapAn;
import com.codegym.cms.repository.DapAnRepository;
import com.codegym.cms.service.DapAnService;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
public class DapAnServiceImpl implements DapAnService {
   @Autowired
   private DapAnRepository dapanRepository;

   @Override
   public DapAn findById(Long id){
       return dapanRepository.findById(id).get();
}
   @Override
   public Iterable<DapAn> findAllByIsDeletedEquals(int isDeleted) {
       return dapanRepository.findAllByIsDeletedEquals(isDeleted);
   }
   @Override
    public void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id){
       dapanRepository.softDelete(deleted_at, deleted_by, id);
}


    @Override
    public void create(@Param("CauHoi_id") Long CauHoi_id, @Param("name") String name, @Param("class1") String class1, @Param("luachon") String luachon, @Param("value") String value, @Param("innerText") String innerText, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by){
       dapanRepository.create(CauHoi_id, name, class1, luachon, value, innerText, created_at, created_by);}
   @Override
    public void edit(@Param("CauHoi_id") Long CauHoi_id, @Param("name") String name, @Param("class1") String class1, @Param("luachon") String luachon, @Param("value") String value, @Param("innerText") String innerText, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long   id){
       dapanRepository.edit(CauHoi_id, name, class1, luachon, value, innerText, updated_at, updated_by, id);
 }

    @Override
    public Iterable<DapAn> findBySite(Long site) {
        return dapanRepository.findBySite(site);
    }
}