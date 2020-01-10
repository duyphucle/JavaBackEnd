package com.codegym.cms.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private int tongdiem;
    private int diemdarut;
    private String diemchoduyet;
    @ManyToOne
    @JoinColumn(name = "ThongTin_id")
    private ThongTin thongtin;
    private String email;
    private String password;
    @ColumnDefault("1")
    private int enabled;
    @ColumnDefault("'USER'")
    private String roles;

    @ColumnDefault("0")
    private int isDeleted;
    private LocalDate deleted_at;
    private String deleted_by;
    private LocalDate updated_at;
    private String updated_by;
    private LocalDate created_at;
    private String created_by;
    @OneToMany(mappedBy = "user")
    private Set<DatHang> dathang;
    @OneToMany(mappedBy = "user")
    private Set<UserDoingSurvey> userdoingsurvey;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTongdiem() {
        return tongdiem;
    }

    public void setTongdiem(int tongdiem) {
        this.tongdiem = tongdiem;
    }

    public int getDiemdarut() {
        return diemdarut;
    }

    public void setDiemdarut(int diemdarut) {
        this.diemdarut = diemdarut;
    }

    public String getDiemchoduyet() {
        return diemchoduyet;
    }

    public void setDiemchoduyet(String diemchoduyet) {
        this.diemchoduyet = diemchoduyet;
    }

    public ThongTin getThongTin() {
        return thongtin;
    }

    public void setThongTin(ThongTin thongtin) {
        this.thongtin = thongtin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public LocalDate getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(LocalDate deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getDeleted_by() {
        return deleted_by;
    }

    public void setDeleted_by(String deleted_by) {
        this.deleted_by = deleted_by;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Set<DatHang> getDatHang() {
        return dathang;
    }

    public void setDatHang(Set<DatHang> dathang) {
        this.dathang = dathang;
    }

    public Set<UserDoingSurvey> getUserDoingSurvey() {
        return userdoingsurvey;
    }

    public void setUserDoingSurvey(Set<UserDoingSurvey> userdoingsurvey) {
        this.userdoingsurvey = userdoingsurvey;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}