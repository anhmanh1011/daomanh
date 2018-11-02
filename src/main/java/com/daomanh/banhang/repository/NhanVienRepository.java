package com.daomanh.banhang.repository;

import com.daomanh.banhang.Entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Transactional
public interface NhanVienRepository extends CrudRepository<NhanVien,Integer> {


    NhanVien findByTenDangNhap(String tenDangNhap);
    boolean existsByEmail(String email);
    boolean existsByTenDangNhap(String tenDangNhap);


}
