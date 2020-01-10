package com.codegym.cms.repository; 
import com.codegym.cms.model.Xe;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import javax.transaction.Transactional;
import java.time.LocalDate;
public interface XeRepository extends PagingAndSortingRepository<Xe, Long> {

    Iterable<Xe> findAllByIsDeletedEquals(int isDeleted);

    @Transactional
    @Modifying
    @Query("UPDATE Xe b SET b.isDeleted = 1, b.deleted_at = :deleted_at, b.deleted_by = :deleted_by WHERE b.id=:id")
    void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Xe ( id, DongXe, idHang, HangXe, created_at, created_by )" + 
               "VALUES ((SELECT IFNULL((SELECT MAX(id) FROM Xe C) + 1, 1 )), :DongXe, :idHang, :HangXe, :created_at, :created_by);", nativeQuery = true)
    void create(@Param("DongXe") String DongXe, @Param("idHang") Long idHang, @Param("HangXe") String HangXe, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


    @Transactional
    @Modifying
    @Query(value = "UPDATE Xe SET DongXe = :DongXe, idHang = :idHang, HangXe = :HangXe, updated_at = :updated_at, updated_by = :updated_by WHERE id = :id", nativeQuery = true)
     void edit(@Param("DongXe") String DongXe, @Param("idHang") Long idHang, @Param("HangXe") String HangXe, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id);

    @Query(value = "SELECT distinct HangXe FROM Xe", nativeQuery = true)
    Iterable<String> hangXe();

    @Query(value = "SELECT DongXe FROM Xe WHERE HangXe = :HangXe", nativeQuery = true)
    Iterable<String> dongXe(@Param("HangXe") String HangXe);

    @Query(value = "SELECT distinct HangXe FROM Xe\n" +
            "WHERE HangXe NOT IN (\"...\")\n" +
            "ORDER BY RAND()", nativeQuery = true)
    Iterable<String> hangXe2();
}
