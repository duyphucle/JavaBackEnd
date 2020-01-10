package com.codegym.cms.repository; 
import com.codegym.cms.model.Ngay;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import javax.transaction.Transactional;
import java.time.LocalDate;
public interface NgayRepository extends PagingAndSortingRepository<Ngay, Long> {

    Iterable<Ngay> findAllByIsDeletedEquals(int isDeleted);

    @Transactional
    @Modifying
    @Query("UPDATE Ngay b SET b.isDeleted = 1, b.deleted_at = :deleted_at, b.deleted_by = :deleted_by WHERE b.id=:id")
    void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Ngay ( id, name, class1, value, luachon, innerText, created_at, created_by )" + 
               "VALUES ((SELECT IFNULL((SELECT MAX(id) FROM Ngay C) + 1, 1 )), :name, :class1, :value, :luachon, :innerText, :created_at, :created_by);", nativeQuery = true)
    void create(@Param("name") String name, @Param("class1") String class1, @Param("value") String value, @Param("luachon") String luachon, @Param("innerText") String innerText, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


    @Transactional
    @Modifying
    @Query(value = "UPDATE Ngay SET name = :name, class1 = :class1, value = :value, luachon = :luachon, innerText = :innerText, updated_at = :updated_at, updated_by = :updated_by WHERE id = :id", nativeQuery = true)
     void edit(@Param("name") String name, @Param("class1") String class1, @Param("value") String value, @Param("luachon") String luachon, @Param("innerText") String innerText, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id);

}
