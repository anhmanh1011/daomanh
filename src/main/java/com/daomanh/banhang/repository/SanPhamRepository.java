package com.daomanh.banhang.repository;

import com.daomanh.banhang.Entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham,Integer> {
    Page<SanPham> findAll(Pageable pageable);




}
