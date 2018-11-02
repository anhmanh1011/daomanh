package com.daomanh.banhang.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "CHUCVU")
public class ChucVu {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int maChucVu;
    String tenChucVu;

    public ChucVu() {
    }

    public ChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }



    public int getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(int maChucVu) {
        this.maChucVu = maChucVu;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }


}
