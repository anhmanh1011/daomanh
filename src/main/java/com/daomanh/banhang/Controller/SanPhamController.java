package com.daomanh.banhang.Controller;

import com.daomanh.banhang.Entity.DanhMucSanPham;
import com.daomanh.banhang.Entity.SanPham;
import com.daomanh.banhang.repository.DanhMucSanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
public class SanPhamController {

    @Autowired
    DanhMucSanPhamRepository danhMucSanPhamRepository;
    private DanhMucSanPhamRepository danhMucSanPhamRepository1;

    @GetMapping("/sanpham")
    public String defaul() {

        return "product";
    }

    @GetMapping("/sanpham/{maDanhMuc}")
    public String SanPham(@PathVariable int maDanhMuc, Model model) {


        DanhMucSanPham danhMucSanPham = danhMucSanPhamRepository.getOne(maDanhMuc);
        List<SanPham> dsSanPham = danhMucSanPham.getDsSanPham();

        model.addAttribute("dsSanPham", dsSanPham);

        List<DanhMucSanPham> listDanhMucSanPham = danhMucSanPhamRepository.findAll();
        model.addAttribute("listDanhMuc", listDanhMucSanPham);
        model.addAttribute("tenDanhMuc", danhMucSanPham.getTenDanhMuc());


        return "product";
    }


}
