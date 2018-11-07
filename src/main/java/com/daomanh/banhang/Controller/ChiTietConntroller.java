package com.daomanh.banhang.Controller;

import com.daomanh.banhang.Entity.DanhMucSanPham;
import com.daomanh.banhang.Entity.GioHang;
import com.daomanh.banhang.Entity.SanPham;
import com.daomanh.banhang.repository.DanhMucSanPhamRepository;
import com.daomanh.banhang.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@SessionAttributes("gioHangs")
public class ChiTietConntroller {


    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    DanhMucSanPhamRepository danhMucSanPhamRepository;

    @GetMapping("chitiet")
    public  String defaul(Model model, Principal principal){
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            model.addAttribute("username",loginedUser.getUsername());

        }
        return "detail";
    }

    @GetMapping("chitiet/{masanpham}")
    public  String chiTietSanPham(@PathVariable int masanpham , Model model, HttpSession httpSession, Principal principal) {

        if(principal != null){
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            model.addAttribute("username",loginedUser.getUsername());

        }
        SanPham sanPham = sanPhamRepository.getOne(masanpham);
        model.addAttribute("ChiTietSanPham",sanPham);
        List<DanhMucSanPham> listDanhMucSanPham = danhMucSanPhamRepository.findAll();
        model.addAttribute("listDanhMuc",listDanhMucSanPham);
        List<GioHang> gioHangs = (List<GioHang>) httpSession.getAttribute("gioHangs");
        if(gioHangs != null){
            model.addAttribute("soLuongGioHang",gioHangs.size());
        }
        return "detail";
    }

}
