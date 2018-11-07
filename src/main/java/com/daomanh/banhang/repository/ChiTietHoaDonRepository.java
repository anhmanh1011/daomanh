package com.daomanh.banhang.repository;

import com.daomanh.banhang.Entity.ChiTietHoaDon;
import com.daomanh.banhang.Entity.ChiTietHoaDonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiTietHoaDonRepository extends JpaRepository<ChiTietHoaDon, ChiTietHoaDonId> {
}
