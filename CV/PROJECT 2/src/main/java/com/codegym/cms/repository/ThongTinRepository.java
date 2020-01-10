package com.codegym.cms.repository; 
import com.codegym.cms.model.ThongTin;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import javax.transaction.Transactional;
import java.time.LocalDate;
public interface ThongTinRepository extends PagingAndSortingRepository<ThongTin, Long> {

    Iterable<ThongTin> findAllByIsDeletedEquals(int isDeleted);

    @Transactional
    @Modifying
    @Query("UPDATE ThongTin b SET b.isDeleted = 1, b.deleted_at = :deleted_at, b.deleted_by = :deleted_by WHERE b.id=:id")
    void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO ThongTin ( id, Ten, Tuoi, CMND, QueQuan, created_at, created_by )" + 
               "VALUES ((SELECT IFNULL((SELECT MAX(id) FROM ThongTin C) + 1, 1 )), :Ten, :Tuoi, :CMND, :QueQuan, :created_at, :created_by);", nativeQuery = true)
    void create(@Param("Ten") String Ten, @Param("Tuoi") int Tuoi, @Param("CMND") String CMND, @Param("QueQuan") String QueQuan, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


    @Transactional
    @Modifying
    @Query(value = "UPDATE ThongTin SET Ten = :Ten, Tuoi = :Tuoi, CMND = :CMND, QueQuan = :QueQuan, updated_at = :updated_at, updated_by = :updated_by WHERE id = :id", nativeQuery = true)
     void edit(@Param("Ten") String Ten, @Param("Tuoi") int Tuoi, @Param("CMND") String CMND, @Param("QueQuan") String QueQuan, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id);

}
