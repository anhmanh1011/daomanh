package com.daomanh.banhang.Controller;

import com.daomanh.banhang.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HoaDOnController {


    @Autowired
    HoaDonRepository hoaDonRepository;


    @GetMapping("hoadon")
    public String hoaDon(Model model) {

        model.addAttribute("listHoaDon", hoaDonRepository.findAll());

        return "HoaDon";

    }
}
