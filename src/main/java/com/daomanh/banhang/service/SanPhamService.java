package com.daomanh.banhang.service;

import com.daomanh.banhang.Entity.SanPham;
import com.daomanh.banhang.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SanPhamService {

    @Autowired
    SanPhamRepository sanPhamRepository;


    @Transactional
    public Page<SanPham> findListProduct() {
        return sanPhamRepository.findAll(new PageRequest(0, 20));
    }

}
