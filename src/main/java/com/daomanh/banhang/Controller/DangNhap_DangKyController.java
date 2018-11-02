package com.daomanh.banhang.Controller;

import com.daomanh.banhang.Entity.ChucVu;
import com.daomanh.banhang.Entity.NhanVien;
import com.daomanh.banhang.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


@Controller
public class DangNhap_DangKyController {


    @Autowired
    NhanVienRepository nhanVienRepository;

    @GetMapping("/login")
    public String login() {
        return "login";

    }

    @PostMapping("/signup")
    public String Dangky(@RequestParam String email, @RequestParam String username, @RequestParam String password, @RequestParam String repassword, Model model) {

        if (!nhanVienRepository.existsByTenDangNhap(username))
            if (isValidEmailAddress(email)) {
                if (password.equals(repassword) && password != "") {

                    NhanVien nv = new NhanVien();
                    nv.setEmail(email);
                    nv.setMatKhau(password);
                    nv.setTenDangNhap(email);
                    nv.setTenDangNhap(username);

                    ChucVu cv = new ChucVu("users");
                    cv.setMaChucVu(1);

                    nv.setChucVu(cv);
                    if (!nhanVienRepository.existsByEmail(email)) {

                        nhanVienRepository.save(nv);
                        model.addAttribute("signup", "Dang Ky Thanh Cong");
                    } else model.addAttribute("signup", "Email da ton tai");
                } else
                    model.addAttribute("signup", "nhập lại passwoed không chính xác");
            } else model.addAttribute("signup", "email của bạn không đúng định dạng");
            else model.addAttribute("signup","Tên đăng nhập đã tồn tại");


        return "login";
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }


}
