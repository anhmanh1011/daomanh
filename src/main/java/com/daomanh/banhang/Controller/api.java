 package com.daomanh.banhang.Controller;


 import com.daomanh.banhang.Entity.*;
 import com.daomanh.banhang.repository.*;
 import com.daomanh.banhang.service.SanPhamService;

 import org.springframework.beans.factory.annotation.Autowired;

 import org.springframework.data.domain.Page;
 import org.springframework.transaction.annotation.Transactional;
 import org.springframework.ui.Model;
 import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.multipart.MultipartHttpServletRequest;


 import javax.persistence.EntityManager;

import javax.servlet.http.HttpSession;
 import java.io.File;
 import java.io.IOException;

 import java.util.*;
import java.util.function.Consumer;

@RestController
@RequestMapping("/api")
@SessionAttributes("gioHangs")
public class api {

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    ChiTietHoaDonRepository chiTietHoaDonRepository;

    @Autowired
    ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    DanhMucSanPhamRepository danhMucSanPhamRepository;




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

    @GetMapping("/themsanpham")
    @ResponseBody
    public String themsanpham(@RequestParam int pageNumber, Model model) {
        System.out.println(pageNumber);

        Page<SanPham> listProduct = sanPhamService.findListProduct(pageNumber - 1, 5);
        List<SanPham> content = listProduct.getContent();
        String html = "";
        for (int i = 0; i < content.size(); i++) {
            html += "<tr>" +
                    "                  <td>\n" +
                    "                      <div class=\"checkbox\">\n" +
                    "                          <label><input class=\"checkboxsanpham\" type=\"checkbox\" value=\" " + content.get(i).getMaSanPham() + "\"></label>\n" +
                    "                      </div>\n" +
                    "\n" +
                    "                  </td>\n" +
                    "\n" +
                    "                  <td> <span class=\"sanpham\" data-masanpham= \" " + content.get(i).getMaSanPham() + "\" > " + content.get(i).getTenSanPham() + " </span></td>\n" +
                    "                  <td> <span class=\"danhmuc\" data-madanhmuc=\" " + content.get(i).getDanhMucSanPham().getMaDanhMuc() + "\" > " + content.get(i).getDanhMucSanPham().getTenDanhMuc() + " </span></td>\n" +
                    "                  <td><span class=\"giaTien\" data-giatien=\" " + content.get(i).getGiaTien() + "\" > " + content.get(i).getGiaTien() + " </span></td>\n" +

                    "\n" +
                    "<td>       <buttion data-maSanPham= \"" + content.get(i).getMaSanPham() + "\"  class=\" btn btn-primary btnSua \"  >  Sửa</buttion></td></tr>";

        }


        return html;
    }

    @Autowired
    EntityManager entityManager;


    @GetMapping("/xoasanpham")
    @ResponseBody
    @Transactional
    public String xoaSanPham(@RequestParam int maSanPham) {

        System.out.println(maSanPham);


        SanPham sanpham = sanPhamRepository.getOne(maSanPham);

        List<ChiTietSanPham> dsChiTietSanPham = sanpham.getDsChiTietSanPham();

        dsChiTietSanPham.forEach(new Consumer<ChiTietSanPham>() {
            @Override
            public void accept(ChiTietSanPham chiTietSanPham) {

                entityManager.createQuery(" DELETE ChiTietHoaDon  WHERE ma_chi_tiet = " + chiTietSanPham.getMaChiTiet()).executeUpdate();
                entityManager.createQuery(" DELETE CHITIETSANPHAM  WHERE ma_chi_tiet = " + chiTietSanPham.getMaChiTiet()).executeUpdate();

            }
        });

        sanPhamRepository.deleteById(maSanPham);
        System.out.println("xoa san pham ma " + maSanPham + " ok");


        return "ok";

    }

    @GetMapping("/xoadanhmuc")
    @ResponseBody
    @Transactional
    public String xoaDanhMuc(@RequestParam int maDanhMuc) {

        System.out.println(maDanhMuc);


        DanhMucSanPham danhMucSanPham = danhMucSanPhamRepository.getOne(maDanhMuc);

        List<SanPham> dsSanpham = danhMucSanPham.getDsSanPham();

        dsSanpham.forEach(new Consumer<SanPham>() {
            @Override
            public void accept(SanPham sanPham) {

                xoaSanPham(sanPham.getMaSanPham());

            }
        });

        danhMucSanPhamRepository.deleteById(maDanhMuc);

        System.out.println("xoa san pham ma " + maDanhMuc + " ok");


        return "true";

    }

    @GetMapping("/xoaHoaDon")
    @ResponseBody
    @Transactional
    public String xoaHoaDon(@RequestParam int maHoaDon) {

        System.out.println(maHoaDon);

        try {


            entityManager.createQuery(" DELETE ChiTietHoaDon  WHERE ma_hoa_don = " + maHoaDon).executeUpdate();


            hoaDonRepository.deleteById(maHoaDon);

            System.out.println("xoa Hoa Don ma " + maHoaDon + " ok");


        } catch (Exception e) {

            e.printStackTrace();
            return "false";


        }


        return "true";

    }


    @PostMapping("upLoadFile")
    @ResponseBody
    public String upLoad(MultipartHttpServletRequest request) {


        ClassLoader classLoader = getClass().getClassLoader();

        Iterator<String> fileNames = request.getFileNames();
        MultipartFile mpf = request.getFile(fileNames.next());
        System.out.println(mpf.getOriginalFilename());


        String path = classLoader.getResource("static/image/").getPath();

        System.out.println(path);
        File file = new File(path + mpf.getOriginalFilename());


        try {
            mpf.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "true";

    }

    @PostMapping("SaveSanPham")
    @ResponseBody
    @Transactional
    public String themSanPham(@ModelAttribute SanPham sp, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            System.out.println(bindingResult.getFieldError());

        }
        System.out.println("save San Pham");
        System.out.println(sp);

        sanPhamRepository.save(sp);


        return "Da Them San Pham Thanh Cong ";
    }


    @PostMapping("SaveDanhMuc")
    @ResponseBody
    @Transactional
    public String themDanhMuc(@ModelAttribute DanhMucSanPham danhMucSanPham, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            System.out.println(bindingResult.getFieldError());

        }
        System.out.println("save Danh Muc ");
        System.out.println(danhMucSanPham);

        danhMucSanPhamRepository.save(danhMucSanPham);


        return "Da Them Danh Muc Thanh Cong ";
    }

    @PostMapping(value = "detailSanPham", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Json_SanPham detailSanPhan(@RequestParam int maSanPham) {

        SanPham sp = sanPhamRepository.getOne(maSanPham);
        if (sp != null) {
            Json_SanPham json_sanPham = new Json_SanPham();
            json_sanPham.setMaSanPham(sp.getMaSanPham());
            json_sanPham.setMoTa(sp.getMoTa());
            json_sanPham.setTenSanPham(sp.getTenSanPham());
            json_sanPham.setHinhSanPham(sp.getHinhSanPham());
            json_sanPham.setGiaTien(sp.getGiaTien());

            DanhMucSanPham dmsp = new DanhMucSanPham();
            dmsp.setMaDanhMuc(sp.getDanhMucSanPham().getMaDanhMuc());
            dmsp.setTenDanhMuc(sp.getDanhMucSanPham().getTenDanhMuc());
            json_sanPham.setDanhMucSanPham(dmsp);
            List<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();

            sp.getDsChiTietSanPham().forEach(new Consumer<ChiTietSanPham>() {
                @Override
                public void accept(ChiTietSanPham chiTietSanPham) {
                    ChiTietSanPham chiTietSanPham1 = new ChiTietSanPham();
                    MauSanPham mauSanPham = new MauSanPham();
                    chiTietSanPham1.setMaChiTiet(chiTietSanPham.getMaChiTiet());
                    mauSanPham.setMaMau(chiTietSanPham.getMauSanPham().getMaMau());
                    mauSanPham.setTenMau(chiTietSanPham.getMauSanPham().getTenMau());
                    chiTietSanPham1.setMauSanPham(mauSanPham);
                    chiTietSanPham1.setNgayNhap(chiTietSanPham.getNgayNhap());
                    chiTietSanPham1.setSoLuong(chiTietSanPham.getSoLuong());
                    chiTietSanPhams.add(chiTietSanPham1);


                }
            });

            json_sanPham.setDsChiTietSanPham(chiTietSanPhams);


            return json_sanPham;
        } else
            return null;
    }

    @PostMapping("updateSanPham")
    @ResponseBody
    @Transactional
    public String updateSanPham(@ModelAttribute SanPham sp) {


        sanPhamRepository.save(sp);
        System.out.println(sp);
        return "Update San Pham Thanh Cong ";
    }

    @PostMapping("updateDanhMuc")
    @ResponseBody
    @Transactional
    public String updateDanhMuc(@ModelAttribute DanhMucSanPham danhMucSanPham) {


        danhMucSanPhamRepository.save(danhMucSanPham);
        System.out.println(danhMucSanPham);
        return "Update Danh muc  Thanh Cong ";
    }

    @PostMapping(value = "detailDanhMuc", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Json_DanhMucSanPham detailDanhMuc(@RequestParam int maDanhMuc) {

        DanhMucSanPham danhMucSanPham = danhMucSanPhamRepository.getOne(maDanhMuc);
        Json_DanhMucSanPham json_danhMucSanPham = new Json_DanhMucSanPham();

        json_danhMucSanPham.setMaDanhMuc(danhMucSanPham.getMaDanhMuc());
        json_danhMucSanPham.setHinhDanhMuc(danhMucSanPham.getHinhDanhMuc());
        json_danhMucSanPham.setTenDanhMuc(danhMucSanPham.getTenDanhMuc());

        return json_danhMucSanPham;

    }

    @PostMapping(value = "changeStatusHoaDon")
    @ResponseBody
    public String changeStatusHoaDon(@RequestParam int maHoaDon, @RequestParam Boolean status) {

        if (maHoaDon != 0) {
            HoaDon hoaDon = hoaDonRepository.getOne(maHoaDon);
            hoaDon.setTinhTrang(status);
            hoaDonRepository.save(hoaDon);
            return "true";
        }

        return "false";

    }






}
