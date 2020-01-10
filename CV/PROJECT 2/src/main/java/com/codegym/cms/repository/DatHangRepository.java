package com.codegym.cms.repository; 
import com.codegym.cms.model.User;
import com.codegym.cms.model.DatHang;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import javax.transaction.Transactional;
import java.time.LocalDate;
public interface DatHangRepository extends PagingAndSortingRepository<DatHang, Long> {

    Iterable<DatHang> findAllByIsDeletedEquals(int isDeleted);

    @Transactional
    @Modifying
    @Query("UPDATE DatHang b SET b.isDeleted = 1, b.deleted_at = :deleted_at, b.deleted_by = :deleted_by WHERE b.id=:id")
    void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO DatHang ( id, User_id, OrderDate, created_at, created_by )" + 
               "VALUES ((SELECT IFNULL((SELECT MAX(id) FROM DatHang C) + 1, 1 )), :User_id, :OrderDate, :created_at, :created_by);", nativeQuery = true)
    void create(@Param("User_id") Long User_id, @Param("OrderDate") LocalDate OrderDate, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


    @Transactional
    @Modifying
    @Query(value = "UPDATE DatHang SET User_id = :User_id, OrderDate = :OrderDate, updated_at = :updated_at, updated_by = :updated_by WHERE id = :id", nativeQuery = true)
     void edit(@Param("User_id") Long User_id, @Param("OrderDate") LocalDate OrderDate, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id);

}
