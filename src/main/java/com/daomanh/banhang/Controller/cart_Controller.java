package com.daomanh.banhang.Controller;


import com.daomanh.banhang.Entity.GioHang;
import com.daomanh.banhang.Entity.MauSanPham;
import com.daomanh.banhang.Entity.SanPham;
import com.daomanh.banhang.repository.MauSanPhamRepository;
import com.daomanh.banhang.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class cart_Controller {

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    MauSanPhamRepository mauSanPhamRepository;

    @GetMapping("/cart")
    public String Default(HttpSession httpSession, ModelMap modelMap){

        if(httpSession.getAttribute("gioHangs") != null) {
            List<GioHang> gioHangs = (List<GioHang>) httpSession.getAttribute("gioHangs");

            for (int i = 0; i < gioHangs.size(); i++) {
                GioHang gioHang = gioHangs.get(i);

                SanPham sanPham = sanPhamRepository.getOne(gioHang.getSanPham().getMaSanPham());
                gioHang.setSanPham(sanPham);
                MauSanPham mauSanPham = mauSanPhamRepository.getOne(gioHang.getMauSanPham().getMaMau());
                gioHang.setMauSanPham(mauSanPham);

            }

            modelMap.addAttribute("gioHang",gioHangs);
        }

        return "Cart";
    }



}
