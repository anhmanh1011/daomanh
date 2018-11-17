package com.daomanh.banhang.Controller;

import com.daomanh.banhang.Entity.DanhMucSanPham;
import com.daomanh.banhang.Entity.NhanVien;
import com.daomanh.banhang.Entity.SanPham;
import com.daomanh.banhang.repository.DanhMucSanPhamRepository;
import com.daomanh.banhang.repository.SanPhamRepository;
import com.daomanh.banhang.service.SanPhamService;
import com.daomanh.banhang.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Controller
public class home_Controller {

//    @GetMapping("/dangnhap")
//    public String dangnhap(){
//        return "login";
//    }


    Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    DanhMucSanPhamRepository danhMucSanPhamRepository;

    @GetMapping("/home")
    public String home(Model model, Principal principal){

        if(principal != null){
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            model.addAttribute("username",loginedUser.getUsername());

        }

        List<SanPham> sanPhams = new ArrayList<>();
        Page<SanPham> listProduct = sanPhamService.findListProduct(0, 20);

        sanPhams = listProduct.getContent();
        model.addAttribute("dsSanPham",sanPhams);

        List<DanhMucSanPham> listDanhMucSanPham = danhMucSanPhamRepository.findAll();
        model.addAttribute("listDanhMuc", listDanhMucSanPham);


        return "home";
    }





}
