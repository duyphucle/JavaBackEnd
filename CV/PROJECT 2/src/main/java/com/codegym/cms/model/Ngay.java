package com.codegym.cms.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Ngay")
public class Ngay{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
private String name;
private String class1;
private String value;
private String luachon;
private String innerText;
@ColumnDefault("0")
    private int isDeleted;
private LocalDate created_at;
private String created_by;
private LocalDate updated_at;
private String updated_by;
private LocalDate deleted_at;
private String deleted_by;
    public Ngay() {}
 public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
 public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
 public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }
 public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
 public String getLuachon() {
        return luachon;
    }

    public void setLuachon(String luachon) {
        this.luachon = luachon;
    }
 public String getInnerText() {
        return innerText;
    }

    public void setInnerText(String innerText) {
        this.innerText = innerText;
    }
 public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
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
}