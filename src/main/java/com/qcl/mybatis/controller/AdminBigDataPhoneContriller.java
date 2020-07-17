package com.qcl.mybatis.controller;

import com.qcl.mybatis.service.BigDataPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mybatis")
public class AdminBigDataPhoneContriller {

    @Autowired
    private BigDataPhoneService bigDataPhoneService;

    @GetMapping("/findAll")
    public void findAll() {
        int count = bigDataPhoneService.count();
        System.out.println(count);
    }


}
