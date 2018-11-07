 package com.daomanh.banhang.Controller;


import com.daomanh.banhang.Entity.GioHang;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping("/api")
@SessionAttributes("gioHangs")
public class api {



    @GetMapping("/themGioHang")
    @ResponseBody
    public String themGioHang(@RequestParam int maSp, @RequestParam int maMau, @RequestParam int soLuong, @RequestParam int maChiTiet, HttpSession httpSession) {


        if( httpSession.getAttribute("gioHangs") == null) {
            GioHang gioHang = new GioHang();
            gioHang.getSanPham().setMaSanPham(maSp);
            gioHang.getMauSanPham().setMaMau(maMau);
            gioHang.setSoLuong(1);
            gioHang.setMaChiTiet(maChiTiet);
            List<GioHang> gioHangs = new ArrayList<>();
            gioHangs.add(gioHang);
            httpSession.setAttribute("gioHangs", gioHangs   );
            System.out.println(gioHang.getSanPham());
            return  gioHangs.size() + "";
        }else{
            List<GioHang> hangList = (List<GioHang>) httpSession.getAttribute("gioHangs");

            int viTri = kiemTraTonTaiSanPham(maSp,maMau,hangList);
            if (viTri == -1) {

                GioHang gioHang = new GioHang();
                gioHang.getSanPham().setMaSanPham(maSp);
                gioHang.getMauSanPham().setMaMau(maMau);
                gioHang.setMaChiTiet(maChiTiet);
                gioHang.setSoLuong(1);
                hangList.add(gioHang);

            }
            else{
                int soLuongMoi = hangList.get(viTri).getSoLuong() + 1;

                hangList.get(viTri).setSoLuong(soLuongMoi);

            }



            return  hangList.size() + "";



        }



    }

    private int kiemTraTonTaiSanPham(int maSanPham, int maMauSanPham, List<GioHang> hangList){


        for (int i = 0; i < hangList.size(); i++) {
            if(hangList.get(i).getSanPham().getMaSanPham() == maSanPham && hangList.get(i).getMauSanPham().getMaMau() == maMauSanPham )
                return i;
        }

        return -1;


    }

    @GetMapping("updateGioHang")
    @ResponseBody
    private void updateGioHang(@RequestParam int maSp, @RequestParam int maMau, @RequestParam int soLuong, HttpSession httpSession) {

        if (httpSession.getAttribute("gioHangs") != null) {
            List<GioHang> hangList = (List<GioHang>) httpSession.getAttribute("gioHangs");
            int vitri = kiemTraTonTaiSanPham(maSp, maMau, hangList);

            hangList.get(vitri).setSoLuong(soLuong);
            hangList.get(vitri).getMauSanPham().setMaMau(maMau);

            hangList.forEach(new Consumer<GioHang>() {
                @Override
                public void accept(GioHang gioHang) {
                    System.out.println(gioHang.getSoLuong());
                }
            });


        }

    }

    @GetMapping("deleteGioHang")
    @ResponseBody
    private String deleteGioHang(@RequestParam int maSp, @RequestParam int maMau, HttpSession httpSession) {

        if (httpSession.getAttribute("gioHangs") != null) {
            List<GioHang> hangList = (List<GioHang>) httpSession.getAttribute("gioHangs");
            int vitri = kiemTraTonTaiSanPham(maSp, maMau, hangList);

            hangList.remove(vitri);
            return "success";


        }
        return "failure";

    }


}
