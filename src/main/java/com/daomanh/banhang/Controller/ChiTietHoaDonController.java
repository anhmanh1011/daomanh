package com.daomanh.banhang.Controller;


import com.daomanh.banhang.Entity.ChiTietHoaDon;
import com.daomanh.banhang.Entity.ChiTietHoaDonModel;
import com.daomanh.banhang.Entity.ChiTietSanPham;
import com.daomanh.banhang.Entity.HoaDon;
import com.daomanh.banhang.repository.ChiTietHoaDonRepository;
import com.daomanh.banhang.repository.ChiTietSanPhamRepository;
import com.daomanh.banhang.repository.HoaDonRepository;
import com.daomanh.banhang.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Controller
public class ChiTietHoaDonController {


    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    ChiTietHoaDonRepository chiTietHoaDonRepository;

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    EntityManager entityManager;

    @GetMapping("ChiTietHoaDon")
    public String ChiTietHoaDon(@RequestParam int maHoaDon, Model model) {

        if (maHoaDon > 0) {
            HoaDon hoaDon = hoaDonRepository.getOne(maHoaDon);


            List<ChiTietHoaDon> resultList = entityManager.createQuery(" SELECT c from  ChiTietHoaDon c   WHERE c.chiTietHoaDonId.maHoaDon = " + maHoaDon).getResultList();

            List<ChiTietHoaDonModel> chiTietHoaDonModelList = new ArrayList<>();

            resultList.forEach(new Consumer<ChiTietHoaDon>() {
                @Override
                public void accept(ChiTietHoaDon chiTietHoaDon) {
                    ChiTietHoaDonModel chiTietHoaDonModel = new ChiTietHoaDonModel();
                    ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.getOne(chiTietHoaDon.getChiTietHoaDonId().getMaChiTiet());
                    chiTietHoaDonModel.setMaSanPham(chiTietSanPham.getSanPham().getMaSanPham());
                    chiTietHoaDonModel.setGiaTien(chiTietHoaDon.getGiaTien());
                    chiTietHoaDonModel.setSoLuong(chiTietHoaDon.getSoLuong());
                    chiTietHoaDonModel.setMauSanPham(chiTietSanPham.getMauSanPham().getTenMau());
                    chiTietHoaDonModel.setTenSanPham(chiTietSanPham.getSanPham().getTenSanPham());

                    chiTietHoaDonModelList.add(chiTietHoaDonModel);
                    System.out.println(chiTietHoaDonModel);
                }
            });


            model.addAttribute("hoaDon", hoaDon);
            model.addAttribute("listChiTiet", chiTietHoaDonModelList);
        }

        return "chitiethoadon";
    }

}
