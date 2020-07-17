package com.qcl.mybatis.service.impl;

import com.qcl.mybatis.mapper.BigDataPhoneMapper;
import com.qcl.mybatis.service.BigDataPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BigDataPhoneImpl implements BigDataPhoneService{

    @Autowired
    private BigDataPhoneMapper bigDataPhoneMapper;

    @Override
    public int count(){
        System.out.println("%");
//        System.out.println(bigDataPhoneService.count());
        return bigDataPhoneMapper.count();
    }

}
