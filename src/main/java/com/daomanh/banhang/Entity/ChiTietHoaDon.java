package com.daomanh.banhang.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "CHITIETHOADON")
public class ChiTietHoaDon implements Serializable {

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "maHoaDon")
    HoaDon hoaDon;

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "maSanPham")
    SanPham sanPham;



    int soLuong;

    int giaTien;





}
