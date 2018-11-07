package com.daomanh.banhang.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ChiTietHoaDon")
public class ChiTietHoaDon implements Serializable {

    @EmbeddedId
    ChiTietHoaDonId chiTietHoaDonId;

    int soLuong;

    int giaTien;


    public ChiTietHoaDon() {
    }


    public ChiTietHoaDonId getChiTietHoaDonId() {
        return chiTietHoaDonId;
    }

    public void setChiTietHoaDonId(ChiTietHoaDonId chiTietHoaDonId) {
        this.chiTietHoaDonId = chiTietHoaDonId;
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
}
