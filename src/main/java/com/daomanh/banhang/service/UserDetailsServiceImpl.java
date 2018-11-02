package com.daomanh.banhang.service;

import com.daomanh.banhang.Entity.ChucVu;
import com.daomanh.banhang.Entity.NhanVien;
import com.daomanh.banhang.repository.ChucVuRepository;
import com.daomanh.banhang.repository.NhanVienRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    ChucVuRepository chucVuRepository;

    @Autowired
    NhanVienRepository nhanvienRepository;


    Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        NhanVien nv = nhanvienRepository.findByTenDangNhap(s);

        if (nv == null) {
            System.out.println("User not found! " + s);
            throw new UsernameNotFoundException("User " + s + " was not found in the database");
        }

        System.out.println("Found User: " + nv.getTenDangNhap());
        ChucVu cv = chucVuRepository.findById(nv.getChucVu().getMaChucVu()).get();

        String roleNames = cv.getTenChucVu();

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        logger.info(nv.getTenDangNhap() + "\t" + nv.getMatKhau()  + "\t " +  cv.getTenChucVu() );
        GrantedAuthority authority = new SimpleGrantedAuthority(roleNames);
        grantList.add(authority);



        UserDetails userDetails = (UserDetails) new User(nv.getTenDangNhap(),
                nv.getMatKhau(), grantList);


        return userDetails;
    }
}
