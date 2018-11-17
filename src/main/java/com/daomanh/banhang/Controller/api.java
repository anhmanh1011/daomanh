 package com.daomanh.banhang.Controller;


 import com.daomanh.banhang.Entity.*;
 import com.daomanh.banhang.repository.ChiTietHoaDonRepository;
 import com.daomanh.banhang.repository.ChiTietSanPhamRepository;
 import com.daomanh.banhang.repository.HoaDonRepository;
 import com.daomanh.banhang.repository.SanPhamRepository;
 import com.daomanh.banhang.service.SanPhamService;
 import org.hibernate.Session;
 import org.hibernate.SessionFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.core.io.ClassPathResource;
 import org.springframework.core.io.PathResource;
 import org.springframework.data.domain.Page;
 import org.springframework.transaction.annotation.Transactional;
 import org.springframework.ui.Model;
 import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.multipart.MultipartHttpServletRequest;
 import org.springframework.web.multipart.MultipartRequest;
 import org.springframework.web.multipart.commons.CommonsMultipartResolver;

 import javax.persistence.EntityManager;
 import javax.persistence.EntityManagerFactory;
 import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
 import java.io.File;
 import java.io.IOException;
 import java.net.MalformedURLException;
 import java.nio.file.Paths;
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
                    "                  <td>\n" +
                    "\n" +
                    "              </tr>";

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
        System.out.println("xoa ok");


        return "ok";

    }


    @Autowired
    ServletContext context;

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

        System.out.println(sp);

        sanPhamRepository.save(sp);


        return "true";
    }



}
