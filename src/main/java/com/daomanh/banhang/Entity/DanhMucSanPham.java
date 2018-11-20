package com.daomanh.banhang.Entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "DANHMUCSANPHAM")
public class DanhMucSanPham {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int maDanhMuc;

    String tenDanhMuc;
    String hinhDanhMuc;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "maDanhMuc")
    List<SanPham> dsSanPham;


    public List<SanPham> getDsSanPham() {
        return dsSanPham;
    }

    public void setDsSanPham(List<SanPham> dsSamPham) {
        this.dsSanPham = dsSamPham;
    }

    public DanhMucSanPham() {
    }

    public DanhMucSanPham(String tenDanhMuc, String hinhDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
        this.hinhDanhMuc = hinhDanhMuc;
    }

    public int getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(int maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public String getHinhDanhMuc() {
        return hinhDanhMuc;
    }

    public void setHinhDanhMuc(String hinhDanhMuc) {
        this.hinhDanhMuc = hinhDanhMuc;
    }

    @Override
    public String toString() {
        return "DanhMucSanPham{" +
                "maDanhMuc=" + maDanhMuc +
                ", tenDanhMuc='" + tenDanhMuc + '\'' +
                ", hinhDanhMuc='" + hinhDanhMuc + '\'' +
                ", dsSanPham=" + dsSanPham +
                '}';
    }
}
