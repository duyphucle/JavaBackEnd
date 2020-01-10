package com.codegym.cms.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "SanPham")
public class SanPham{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
private String TenSanPham;
    @ManyToOne
    @JoinColumn(name = "NhaCungCap_id")
    private NhaCungCap nhacungcap;
    @ManyToOne
    @JoinColumn(name = "LoaiSanPham_id")
    private LoaiSanPham loaisanpham;
private String MoTa;
@ColumnDefault("0")
    private int isDeleted;
private LocalDate deleted_at;
private String deleted_by;
private LocalDate updated_at;
private String updated_by;
private LocalDate created_at;
private String created_by;
    @OneToMany(mappedBy = "sanpham")
    private Set<NhapKho> nhapkho;
    @OneToMany(mappedBy = "sanpham")
    private Set<SellTheoSanPham> selltheosanpham;
    public SanPham() {}
 public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
 public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.TenSanPham = TenSanPham;
    }
    public NhaCungCap getNhaCungCap() {
        return nhacungcap;
    }

    public void setNhaCungCap(NhaCungCap nhacungcap) {
        this.nhacungcap = nhacungcap;
    }
    public LoaiSanPham getLoaiSanPham() {
        return loaisanpham;
    }

    public void setLoaiSanPham(LoaiSanPham loaisanpham) {
        this.loaisanpham = loaisanpham;
    }
 public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
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
    public Set<NhapKho> getNhapKho() {
        return nhapkho;
    }

    public void setNhapKho(Set<NhapKho> nhapkho) {
        this.nhapkho = nhapkho;
    }
    public Set<SellTheoSanPham> getSellTheoSanPham() {
        return selltheosanpham;
    }

    public void setSellTheoSanPham(Set<SellTheoSanPham> selltheosanpham) {
        this.selltheosanpham = selltheosanpham;
    }
}