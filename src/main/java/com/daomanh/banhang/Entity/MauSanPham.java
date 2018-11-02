package com.daomanh.banhang.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "MAUSANPHAM")
public class MauSanPham {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int maMau;
    String tenMau;

    public MauSanPham(){}

    public MauSanPham(String tenMau) {
        this.tenMau = tenMau;
    }

    public int getMaMau() {
        return maMau;
    }

    public void setMaMau(int maMau) {
        this.maMau = maMau;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    @Override
    public String toString() {
        return "MauSanPham{" +
                "maMau=" + maMau +
                ", tenMau='" + tenMau + '\'' +
                '}';
    }
}
