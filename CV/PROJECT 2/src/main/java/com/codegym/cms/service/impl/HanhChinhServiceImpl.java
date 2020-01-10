package com.codegym.cms.service.impl;
import com.codegym.cms.model.HanhChinh;
import com.codegym.cms.repository.HanhChinhRepository;
import com.codegym.cms.service.HanhChinhService;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
public class HanhChinhServiceImpl implements HanhChinhService {
   @Autowired
   private HanhChinhRepository hanhchinhRepository;

   @Override
   public HanhChinh findById(Long id){
       return hanhchinhRepository.findById(id).get();
}
   @Override
   public Iterable<HanhChinh> findAllByIsDeletedEquals(int isDeleted) {
       return hanhchinhRepository.findAllByIsDeletedEquals(isDeleted);
   }
   @Override
    public void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id){
       hanhchinhRepository.softDelete(deleted_at, deleted_by, id);
}


    @Override
    public void create(@Param("TenPhuong") String TenPhuong, @Param("Cap") String Cap, @Param("MAQH") Long MAQH, @Param("QuanHuyen") String QuanHuyen, @Param("MaTP") Long MaTP, @Param("ThanhPho") String ThanhPho, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by){
       hanhchinhRepository.create(TenPhuong, Cap, MAQH, QuanHuyen, MaTP, ThanhPho, created_at, created_by);}
   @Override
    public void edit(@Param("TenPhuong") String TenPhuong, @Param("Cap") String Cap, @Param("MAQH") Long MAQH, @Param("QuanHuyen") String QuanHuyen, @Param("MaTP") Long MaTP, @Param("ThanhPho") String ThanhPho, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long   id){
       hanhchinhRepository.edit(TenPhuong, Cap, MAQH, QuanHuyen, MaTP, ThanhPho, updated_at, updated_by, id);
 }
}