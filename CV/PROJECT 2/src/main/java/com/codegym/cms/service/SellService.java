package com.codegym.cms.service;
import com.codegym.cms.model.Sell;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
public interface SellService {
   Sell findById(Long id);
   Iterable<Sell> findAllByIsDeletedEquals(int isDeleted);
   void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


   void create(@Param("TenChienDich") String TenChienDich, @Param("PhanTramSale") String PhanTramSale, @Param("NgayBatDau") LocalDate NgayBatDau, @Param("NgayKetThuc") LocalDate NgayKetThuc, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


   void edit(@Param("TenChienDich") String TenChienDich, @Param("PhanTramSale") String PhanTramSale, @Param("NgayBatDau") LocalDate NgayBatDau, @Param("NgayKetThuc") LocalDate NgayKetThuc, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id );

}
