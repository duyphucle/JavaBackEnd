package com.codegym.cms.service.impl;
import com.codegym.cms.model.SanPham;
import com.codegym.cms.model.Sell;
import com.codegym.cms.model.SellTheoSanPham;
import com.codegym.cms.repository.SellTheoSanPhamRepository;
import com.codegym.cms.service.SellTheoSanPhamService;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
public class SellTheoSanPhamServiceImpl implements SellTheoSanPhamService {
   @Autowired
   private SellTheoSanPhamRepository selltheosanphamRepository;

   @Override
   public SellTheoSanPham findById(Long id){
       return selltheosanphamRepository.findById(id).get();
}
   @Override
   public Iterable<SellTheoSanPham> findAllByIsDeletedEquals(int isDeleted) {
       return selltheosanphamRepository.findAllByIsDeletedEquals(isDeleted);
   }
   @Override
    public void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id){
       selltheosanphamRepository.softDelete(deleted_at, deleted_by, id);
}


    @Override
    public void create(@Param("Sell_id") Long Sell_id, @Param("SanPham_id") Long SanPham_id, @Param("Gia") Long Gia, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by){
       selltheosanphamRepository.create(Sell_id, SanPham_id, Gia, created_at, created_by);}
   @Override
    public void edit(@Param("Sell_id") Long Sell_id, @Param("SanPham_id") Long SanPham_id, @Param("Gia") Long Gia, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long   id){
       selltheosanphamRepository.edit(Sell_id, SanPham_id, Gia, updated_at, updated_by, id);
 }
}