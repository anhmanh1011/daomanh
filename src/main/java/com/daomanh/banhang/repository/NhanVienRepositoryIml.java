package com.daomanh.banhang.repository;


import com.daomanh.banhang.Entity.NhanVien;
import org.springframework.stereotype.Repository;

@Repository
public class NhanVienRepositoryIml implements NhanVienRepositoryCustom {
    @Override
    public NhanVien findByTen_Dang_Nhap(String tenDangNhap) {
        return null;
    }
}

