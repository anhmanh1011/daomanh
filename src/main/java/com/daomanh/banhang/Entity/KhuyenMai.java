package com.daomanh.banhang.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "KHUYENMAI")
public class KhuyenMai {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int maKhuyenMai;
    String tenKhuyenMai;
    String thoiGianbatDau;
    String thoiGianKetThuc;
    String moTa;
    String hinhKhuyenMai;
    int giaGiam;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CHITIETKHUYENMAI",
            joinColumns ={@JoinColumn(name = "maKhuyenMai",referencedColumnName = "maKhuyenMai")},
            inverseJoinColumns ={@JoinColumn(name = "maSanPham",referencedColumnName = "maSanPham")} )
    Set<SanPham> danhSachSanPham;


    public KhuyenMai(){}
    public KhuyenMai(String tenKhuyenMai, String thoiGianbatDau, String thoiGianKetThuc, String moTa, String hinhKhuyenMai, int giaGiam, Set<SanPham> danhSachSanPham) {
        this.tenKhuyenMai = tenKhuyenMai;
        this.thoiGianbatDau = thoiGianbatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.moTa = moTa;
        this.hinhKhuyenMai = hinhKhuyenMai;
        this.giaGiam = giaGiam;
        this.danhSachSanPham = danhSachSanPham;
    }

    public int getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(int maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public String getThoiGianbatDau() {
        return thoiGianbatDau;
    }

    public void setThoiGianbatDau(String thoiGianbatDau) {
        this.thoiGianbatDau = thoiGianbatDau;
    }

    public String getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(String thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhKhuyenMai() {
        return hinhKhuyenMai;
    }

    public void setHinhKhuyenMai(String hinhKhuyenMai) {
        this.hinhKhuyenMai = hinhKhuyenMai;
    }

    public int getGiaGiam() {
        return giaGiam;
    }

    public void setGiaGiam(int giaGiam) {
        this.giaGiam = giaGiam;
    }

    public Set<SanPham> getDanhSachSanPham() {
        return danhSachSanPham;
    }

    public void setDanhSachSanPham(Set<SanPham> danhSachSanPham) {
        this.danhSachSanPham = danhSachSanPham;
    }



}
