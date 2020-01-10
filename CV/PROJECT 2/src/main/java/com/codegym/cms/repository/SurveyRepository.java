package com.codegym.cms.repository; 
import com.codegym.cms.model.Survey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import javax.transaction.Transactional;
import java.time.LocalDate;
public interface SurveyRepository extends PagingAndSortingRepository<Survey, Long> {

    Iterable<Survey> findAllByIsDeletedEquals(int isDeleted);

    @Transactional
    @Modifying
    @Query("UPDATE Survey b SET b.isDeleted = 1, b.deleted_at = :deleted_at, b.deleted_by = :deleted_by WHERE b.id=:id")
    void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Survey ( id, tenkhaosat, diem, created_at, created_by )" + 
               "VALUES ((SELECT IFNULL((SELECT MAX(id) FROM Survey C) + 1, 1 )), :tenkhaosat, :diem, :created_at, :created_by);", nativeQuery = true)
    void create(@Param("tenkhaosat") String tenkhaosat, @Param("diem") int diem, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by);


    @Transactional
    @Modifying
    @Query(value = "UPDATE Survey SET tenkhaosat = :tenkhaosat, diem = :diem, updated_at = :updated_at, updated_by = :updated_by WHERE id = :id", nativeQuery = true)
     void edit(@Param("tenkhaosat") String tenkhaosat, @Param("diem") int diem, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id);

}
