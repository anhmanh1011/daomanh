package com.daomanh.banhang.Controller;

import com.daomanh.banhang.Entity.DanhMucSanPham;
import com.daomanh.banhang.Entity.SanPham;
import com.daomanh.banhang.repository.DanhMucSanPhamRepository;
import com.daomanh.banhang.repository.MauSanPhamRepository;
import com.daomanh.banhang.repository.SanPhamRepository;
import com.daomanh.banhang.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThemSanPhamController {

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    DanhMucSanPhamRepository danhMucSanPhamRepository;

    @Autowired
    MauSanPhamRepository mauSanPhamRepository;

    @GetMapping("/themsanpham")
    public String themsanpham(Model model) {

        Page<SanPham> listProduct = sanPhamService.findListProduct(0, 5);

        model.addAttribute("listProduct", listProduct);
        long lenght = sanPhamRepository.count();
        long count = lenght / 5;
        if (lenght % 5 > 0)
            count++;

        model.addAttribute("count", count);

        model.addAttribute("listDanhMuc", danhMucSanPhamRepository.findAll());
        model.addAttribute("listMau", mauSanPhamRepository.findAll());


        return "Themsanpham";
    }

}
