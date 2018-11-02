package com.daomanh.banhang.repository;

import com.daomanh.banhang.Entity.DanhMucSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanhMucSanPhamRepository extends JpaRepository<DanhMucSanPham,Integer> {
}
