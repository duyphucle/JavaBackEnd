package com.codegym.cms.service;
import com.codegym.cms.model.HanhChinh;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
public interface HanhChinhService {
   HanhChinh findById(Long id);
   Iterable<HanhChinh> findAllByIsDeletedEquals(int isDeleted);
   void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


   void create(@Param("TenPhuong") String TenPhuong, @Param("Cap") String Cap, @Param("MAQH") Long MAQH, @Param("QuanHuyen") String QuanHuyen, @Param("MaTP") Long MaTP, @Param("ThanhPho") String ThanhPho, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


   void edit(@Param("TenPhuong") String TenPhuong, @Param("Cap") String Cap, @Param("MAQH") Long MAQH, @Param("QuanHuyen") String QuanHuyen, @Param("MaTP") Long MaTP, @Param("ThanhPho") String ThanhPho, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id );

}
