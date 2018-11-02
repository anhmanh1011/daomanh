package com.daomanh.banhang.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "SANPHAM")
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int maSanPham;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "maDanhMuc ", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    DanhMucSanPham danhMucSanPham;


    String tenSanPham;
    String giaTien;
    String moTa;
    String hinhSanPham;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "maSanPham")
    Set<ChiTietSanPham> dsChiTietSanPham;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CHITIETKHUYENMAI",
            joinColumns ={@JoinColumn(name = "maSanPham",referencedColumnName = "maSanPham")},
            inverseJoinColumns ={@JoinColumn(name = "maKhuyenMai",referencedColumnName = "maKhuyenMai")} )

    Set<KhuyenMai> danhSachKhuyenMai;

    public SanPham() {
    }

    public SanPham(DanhMucSanPham danhMucSanPham, String tenSanPham, String giaTien, String moTa, String hinhSanPham, Set<ChiTietSanPham> dsChiTietSanPham, Set<KhuyenMai> danhSachKhuyenMai) {
        this.danhMucSanPham = danhMucSanPham;
        this.tenSanPham = tenSanPham;
        this.giaTien = giaTien;
        this.moTa = moTa;
        this.hinhSanPham = hinhSanPham;
        this.dsChiTietSanPham = dsChiTietSanPham;
        this.danhSachKhuyenMai = danhSachKhuyenMai;
    }

    public Set<ChiTietSanPham> getDsChiTietSanPham() {
        return dsChiTietSanPham;
    }

    public void setDsChiTietSanPham(Set<ChiTietSanPham> dsChiTietSanPham) {
        this.dsChiTietSanPham = dsChiTietSanPham;
    }

    public Set<KhuyenMai> getDanhSachKhuyenMai() {
        return danhSachKhuyenMai;
    }

    public void setDanhSachKhuyenMai(Set<KhuyenMai> danhSachKhuyenMai) {
        this.danhSachKhuyenMai = danhSachKhuyenMai;
    }



    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public DanhMucSanPham getDanhMucSanPham() {
        return danhMucSanPham;
    }

    public void setDanhMucSanPham(DanhMucSanPham danhMucSanPham) {
        this.danhMucSanPham = danhMucSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhSanPham() {
        return hinhSanPham;
    }

    public void setHinhSanPham(String hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "maSanPham=" + maSanPham +
                ", danhMucSanPham=" + danhMucSanPham +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", giaTien='" + giaTien + '\'' +
                ", moTa='" + moTa + '\'' +
                ", hinhSanPham='" + hinhSanPham + '\'' +
                ", dsChiTietSanPham=" + dsChiTietSanPham +
                ", danhSachKhuyenMai=" + danhSachKhuyenMai +
                '}';
    }
}
