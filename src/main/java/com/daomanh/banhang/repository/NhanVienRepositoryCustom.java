package com.daomanh.banhang.repository;

import com.daomanh.banhang.Entity.NhanVien;

public interface NhanVienRepositoryCustom {

    NhanVien findByTen_Dang_Nhap(String tenDangNhap);
}
