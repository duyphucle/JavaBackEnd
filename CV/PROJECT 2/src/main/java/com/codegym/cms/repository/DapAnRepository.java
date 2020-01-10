package com.codegym.cms.repository; 
import com.codegym.cms.model.CauHoi;
import com.codegym.cms.model.DapAn;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import javax.transaction.Transactional;
import java.time.LocalDate;
public interface DapAnRepository extends PagingAndSortingRepository<DapAn, Long> {

    Iterable<DapAn> findAllByIsDeletedEquals(int isDeleted);

    @Transactional
    @Modifying
    @Query("UPDATE DapAn b SET b.isDeleted = 1, b.deleted_at = :deleted_at, b.deleted_by = :deleted_by WHERE b.id=:id")
    void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO DapAn ( id, CauHoi_id, name, class1, luachon, value, innerText, created_at, created_by )" + 
               "VALUES ((SELECT IFNULL((SELECT MAX(id) FROM DapAn C) + 1, 1 )), :CauHoi_id, :name, :class1, :luachon, :value, :innerText, :created_at, :created_by);", nativeQuery = true)
    void create(@Param("CauHoi_id") Long CauHoi_id, @Param("name") String name, @Param("class1") String class1, @Param("luachon") String luachon, @Param("value") String value, @Param("innerText") String innerText, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


    @Transactional
    @Modifying
    @Query(value = "UPDATE DapAn SET CauHoi_id = :CauHoi_id, name = :name, class1 = :class1, luachon = :luachon, value = :value, innerText = :innerText, updated_at = :updated_at, updated_by = :updated_by WHERE id = :id", nativeQuery = true)
     void edit(@Param("CauHoi_id") Long CauHoi_id, @Param("name") String name, @Param("class1") String class1, @Param("luachon") String luachon, @Param("value") String value, @Param("innerText") String innerText, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id);

    @Query(value = "select DapAn.* from DapAn inner join CauHoi on CauHoi.id = DapAn.CauHoi_id where CauHoi.site =?1", nativeQuery = true)
    Iterable<DapAn> findBySite(@Param("site") Long site);
}
