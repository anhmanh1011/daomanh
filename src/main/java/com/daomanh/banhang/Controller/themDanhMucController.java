package com.daomanh.banhang.Controller;

import com.daomanh.banhang.repository.DanhMucSanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class themDanhMucController {


    @Autowired
    DanhMucSanPhamRepository danhMucRepository;

    @GetMapping("/themDanhMuc")
    public String themDanhMuc(Model model) {

        model.addAttribute("listDanhMuc", danhMucRepository.findAll());

        long lenght = danhMucRepository.count();
        long count = lenght / 5;
        if (lenght % 5 > 0)
            count++;

        model.addAttribute("count", count);

        return "ThemDanhMuc";


    }
}
