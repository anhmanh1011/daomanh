package com.daomanh.banhang.Entity;


import javax.persistence.*;
import java.util.Set;

@Entity(name = "CHITIETSANPHAM")
public class ChiTietSanPham {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maChiTiet")
    int maChiTiet;

    @OneToOne()
    @JoinColumn(name = "maSanPham")
    SanPham sanPham;

    @OneToOne()
    @JoinColumn(name = "maSize")
    SizeSanPham sizeSanPham;

    @OneToOne()
    @JoinColumn(name = "maMau")
    MauSanPham mauSanPham;

    int soLuong;
    String ngayNhap;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "maChiTiet")
    Set<ChiTietHoaDon> danhSachChiTietHoaDon;

    public ChiTietSanPham() {
    }

    public ChiTietSanPham(SanPham sanPham, SizeSanPham sizeSanPham, MauSanPham mauSanPham, int soLuong, String ngayNhap) {
        this.sanPham = sanPham;
        this.sizeSanPham = sizeSanPham;
        this.mauSanPham = mauSanPham;
        this.soLuong = soLuong;
        this.ngayNhap = ngayNhap;
    }

    public int getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(int maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public SizeSanPham getSizeSanPham() {
        return sizeSanPham;
    }

    public void setSizeSanPham(SizeSanPham sizeSanPham) {
        this.sizeSanPham = sizeSanPham;
    }

    public MauSanPham getMauSanPham() {
        return mauSanPham;
    }

    public void setMauSanPham(MauSanPham mauSanPham) {
        this.mauSanPham = mauSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    @Override
    public String toString() {
        return "ChiTietSanPham{" +
                "maChiTiet=" + maChiTiet +
                ", sanPham=" + sanPham +
                ", sizeSanPham=" + sizeSanPham +
                ", mauSanPham=" + mauSanPham +
                ", soLuong=" + soLuong +
                ", ngayNhap='" + ngayNhap + '\'' +
                ", danhSachChiTietHoaDon=" + danhSachChiTietHoaDon +
                '}';
    }
}
