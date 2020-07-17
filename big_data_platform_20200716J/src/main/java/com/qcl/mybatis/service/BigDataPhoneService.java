package com.qcl.mybatis.service;

import com.qcl.mybatis.mapper.BigDataPhoneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public interface BigDataPhoneService {

    int count();


}
