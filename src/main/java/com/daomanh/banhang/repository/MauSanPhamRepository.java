package com.daomanh.banhang.repository;

import com.daomanh.banhang.Entity.MauSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MauSanPhamRepository extends JpaRepository<MauSanPham,Integer> {
}
