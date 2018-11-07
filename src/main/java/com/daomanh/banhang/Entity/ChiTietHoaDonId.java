package com.daomanh.banhang.Entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Embeddable
public class ChiTietHoaDonId implements Serializable {


    private int maChiTiet;
    private int maHoaDon;

    public ChiTietHoaDonId() {
    }

    public int getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(int maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }
}
