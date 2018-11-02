package com.daomanh.banhang.repository;

import com.daomanh.banhang.Entity.ChucVu;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface ChucVuRepository extends JpaRepository<ChucVu,Integer> {


}
