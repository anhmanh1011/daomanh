package com.daomanh.banhang.Controller;


import com.daomanh.banhang.Entity.*;
import com.daomanh.banhang.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.function.Consumer;

@Controller
public class cart_Controller {

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    MauSanPhamRepository mauSanPhamRepository;

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    ChiTietHoaDonRepository chiTietHoaDonRepository;

    @Autowired
    ChiTietSanPhamRepository chiTietSanPhamRepository;


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

    @PostMapping("/cart")
    public String themHoaDon(HoaDon hoaDon, HttpSession httpSession, Model model) {


        hoaDon.setNgaylap(new Date().toString());
        hoaDon.setTinhTrang(false);


        if (httpSession.getAttribute("gioHangs") != null) {


            hoaDonRepository.save(hoaDon);
            hoaDonRepository.flush();
            System.out.println(hoaDon);

            List<GioHang> hangList = (List<GioHang>) httpSession.getAttribute("gioHangs");
            hangList.forEach(new Consumer<GioHang>() {
                @Override
                public void accept(GioHang gioHang) {
                    System.out.println(gioHang.getMaChiTiet());
                }
            });

            for (GioHang gioHang : hangList) {
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();

                ChiTietHoaDonId chiTietHoaDonId = new ChiTietHoaDonId();

                chiTietHoaDonId.setMaHoaDon(hoaDon.getMaHoaDon());

                chiTietHoaDonId.setMaChiTiet(gioHang.getMaChiTiet());

                chiTietHoaDon.setChiTietHoaDonId(chiTietHoaDonId);


                chiTietHoaDon.setSoLuong(gioHang.getSoLuong());
                chiTietHoaDon.setGiaTien(gioHang.getSoLuong() * Integer.valueOf(gioHang.getSanPham().getGiaTien()));
                chiTietHoaDonRepository.save(chiTietHoaDon);

            }

            model.addAttribute("result", "1");

        }


        return "Cart";
    }


}
