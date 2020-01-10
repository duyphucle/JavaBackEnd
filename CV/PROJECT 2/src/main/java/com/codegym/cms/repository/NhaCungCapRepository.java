package com.codegym.cms.repository; 
import com.codegym.cms.model.NhaCungCap;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import javax.transaction.Transactional;
import java.time.LocalDate;
public interface NhaCungCapRepository extends PagingAndSortingRepository<NhaCungCap, Long> {

    Iterable<NhaCungCap> findAllByIsDeletedEquals(int isDeleted);

    @Transactional
    @Modifying
    @Query("UPDATE NhaCungCap b SET b.isDeleted = 1, b.deleted_at = :deleted_at, b.deleted_by = :deleted_by WHERE b.id=:id")
    void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO NhaCungCap ( id, TenNhaCungCap, DiaChi, ThanhPho, QuocGia, MaBuuDien, SDT, created_at, created_by )" + 
               "VALUES ((SELECT IFNULL((SELECT MAX(id) FROM NhaCungCap C) + 1, 1 )), :TenNhaCungCap, :DiaChi, :ThanhPho, :QuocGia, :MaBuuDien, :SDT, :created_at, :created_by);", nativeQuery = true)
    void create(@Param("TenNhaCungCap") String TenNhaCungCap, @Param("DiaChi") String DiaChi, @Param("ThanhPho") String ThanhPho, @Param("QuocGia") String QuocGia, @Param("MaBuuDien") String MaBuuDien, @Param("SDT") String SDT, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


    @Transactional
    @Modifying
    @Query(value = "UPDATE NhaCungCap SET TenNhaCungCap = :TenNhaCungCap, DiaChi = :DiaChi, ThanhPho = :ThanhPho, QuocGia = :QuocGia, MaBuuDien = :MaBuuDien, SDT = :SDT, updated_at = :updated_at, updated_by = :updated_by WHERE id = :id", nativeQuery = true)
     void edit(@Param("TenNhaCungCap") String TenNhaCungCap, @Param("DiaChi") String DiaChi, @Param("ThanhPho") String ThanhPho, @Param("QuocGia") String QuocGia, @Param("MaBuuDien") String MaBuuDien, @Param("SDT") String SDT, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id);

}
