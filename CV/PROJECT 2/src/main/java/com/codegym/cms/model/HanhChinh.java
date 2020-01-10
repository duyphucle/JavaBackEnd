package com.codegym.cms.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "HanhChinh")
public class HanhChinh{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
private String TenPhuong;
private String Cap;
private Long MAQH;
private String QuanHuyen;
private Long MaTP;
private String ThanhPho;
@ColumnDefault("0")
    private int isDeleted;
private LocalDate deleted_at;
private String deleted_by;
private LocalDate updated_at;
private String updated_by;
private LocalDate created_at;
private String created_by;
    public HanhChinh() {}
 public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
 public String getTenPhuong() {
        return TenPhuong;
    }

    public void setTenPhuong(String TenPhuong) {
        this.TenPhuong = TenPhuong;
    }
 public String getCap() {
        return Cap;
    }

    public void setCap(String Cap) {
        this.Cap = Cap;
    }
 public Long getMAQH() {
        return MAQH;
    }

    public void setMAQH(Long MAQH) {
        this.MAQH = MAQH;
    }
 public String getQuanHuyen() {
        return QuanHuyen;
    }

    public void setQuanHuyen(String QuanHuyen) {
        this.QuanHuyen = QuanHuyen;
    }
 public Long getMaTP() {
        return MaTP;
    }

    public void setMaTP(Long MaTP) {
        this.MaTP = MaTP;
    }
 public String getThanhPho() {
        return ThanhPho;
    }

    public void setThanhPho(String ThanhPho) {
        this.ThanhPho = ThanhPho;
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
}