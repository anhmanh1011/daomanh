package com.daomanh.banhang.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "NHANVIEN")
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int maNhanVien;
    String hoTen;
    String diaChi;
    String cmnd;

    @OneToOne
    @JoinColumn(name = "maChucVu", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    ChucVu chucVu;

    String email;
    String tenDangNhap;
    String matKhau;

    public NhanVien() {
    }

    public NhanVien(String hoTen, String diaChi, String cmnd, ChucVu chucVu, String email, String tenDangNhap, String matKhau) {
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.cmnd = cmnd;
        this.chucVu = chucVu;
        this.email = email;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }




}
