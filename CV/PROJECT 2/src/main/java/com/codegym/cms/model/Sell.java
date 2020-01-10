package com.codegym.cms.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Sell")
public class Sell{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
private String TenChienDich;
private String PhanTramSale;
private LocalDate NgayBatDau;
private LocalDate NgayKetThuc;
@ColumnDefault("0")
    private int isDeleted;
private LocalDate deleted_at;
private String deleted_by;
private LocalDate updated_at;
private String updated_by;
private LocalDate created_at;
private String created_by;
    @OneToMany(mappedBy = "sell")
    private Set<SellTheoSanPham> selltheosanpham;
    public Sell() {}
 public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
 public String getTenChienDich() {
        return TenChienDich;
    }

    public void setTenChienDich(String TenChienDich) {
        this.TenChienDich = TenChienDich;
    }
 public String getPhanTramSale() {
        return PhanTramSale;
    }

    public void setPhanTramSale(String PhanTramSale) {
        this.PhanTramSale = PhanTramSale;
    }
 public LocalDate getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(LocalDate NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }
 public LocalDate getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(LocalDate NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
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
    public Set<SellTheoSanPham> getSellTheoSanPham() {
        return selltheosanpham;
    }

    public void setSellTheoSanPham(Set<SellTheoSanPham> selltheosanpham) {
        this.selltheosanpham = selltheosanpham;
    }
}