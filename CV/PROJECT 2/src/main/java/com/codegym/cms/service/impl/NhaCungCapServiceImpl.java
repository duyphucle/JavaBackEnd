package com.codegym.cms.service.impl;
import com.codegym.cms.model.NhaCungCap;
import com.codegym.cms.repository.NhaCungCapRepository;
import com.codegym.cms.service.NhaCungCapService;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
public class NhaCungCapServiceImpl implements NhaCungCapService {
   @Autowired
   private NhaCungCapRepository nhacungcapRepository;

   @Override
   public NhaCungCap findById(Long id){
       return nhacungcapRepository.findById(id).get();
}
   @Override
   public Iterable<NhaCungCap> findAllByIsDeletedEquals(int isDeleted) {
       return nhacungcapRepository.findAllByIsDeletedEquals(isDeleted);
   }
   @Override
    public void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id){
       nhacungcapRepository.softDelete(deleted_at, deleted_by, id);
}


    @Override
    public void create(@Param("TenNhaCungCap") String TenNhaCungCap, @Param("DiaChi") String DiaChi, @Param("ThanhPho") String ThanhPho, @Param("QuocGia") String QuocGia, @Param("MaBuuDien") String MaBuuDien, @Param("SDT") String SDT, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by){
       nhacungcapRepository.create(TenNhaCungCap, DiaChi, ThanhPho, QuocGia, MaBuuDien, SDT, created_at, created_by);}
   @Override
    public void edit(@Param("TenNhaCungCap") String TenNhaCungCap, @Param("DiaChi") String DiaChi, @Param("ThanhPho") String ThanhPho, @Param("QuocGia") String QuocGia, @Param("MaBuuDien") String MaBuuDien, @Param("SDT") String SDT, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long   id){
       nhacungcapRepository.edit(TenNhaCungCap, DiaChi, ThanhPho, QuocGia, MaBuuDien, SDT, updated_at, updated_by, id);
 }
}