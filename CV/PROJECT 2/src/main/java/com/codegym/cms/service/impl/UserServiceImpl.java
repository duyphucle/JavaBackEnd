package com.codegym.cms.service.impl;

import com.codegym.cms.model.ThongTin;
import com.codegym.cms.model.User;
import com.codegym.cms.repository.UserRepository;
import com.codegym.cms.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Iterable<User> findAllByIsDeletedEquals(int isDeleted) {
        return userRepository.findAllByIsDeletedEquals(isDeleted);
    }

    @Override
    public void softDelete(@Param("deleted_at") LocalDate deleted_at, @Param("deleted_by") String deleted_by, @Param("id") Long id) {
        userRepository.softDelete(deleted_at, deleted_by, id);
    }


    @Override
    public void create(@Param("username") String username, @Param("tongdiem") int tongdiem, @Param("diemdarut") int diemdarut, @Param("diemchoduyet") String diemchoduyet, @Param("ThongTin_id") Long ThongTin_id, @Param("email") String email, @Param("password") String password, @Param("created_at") LocalDate created_at, @Param("created_by") String created_by) {
        userRepository.create(username, tongdiem, diemdarut, diemchoduyet, ThongTin_id, email, password, created_at, created_by);
    }

    @Override
    public void edit(@Param("username") String username, @Param("tongdiem") int tongdiem, @Param("diemdarut") int diemdarut, @Param("diemchoduyet") String diemchoduyet, @Param("ThongTin_id") Long ThongTin_id, @Param("email") String email, @Param("password") String password, @Param("updated_at") LocalDate updated_at, @Param("updated_by") String updated_by, @Param("id") Long id) {
        userRepository.edit(username, tongdiem, diemdarut, diemchoduyet, ThongTin_id, email, password, updated_at, updated_by, id);
    }

    @Override
    public Long idUser(String email) {
        return userRepository.idUser(email);
    }
}