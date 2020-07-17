package com.qcl.controller;

import com.qcl.bean.OperationLog;
import com.qcl.bean.big_data_phone;
import com.qcl.repository.LogInfoRepository;
import com.qcl.repository.big_data_phone_Repository;
import com.qcl.request.big_data_phone_Req;
import com.qcl.utils.ExcelExportUtils;
import com.qcl.utils.ExcelImportUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * 牛亚博
 * 更新时间：2020年7月14日21:28:22
 * 大数据手机号增加
 */
@Controller
@RequestMapping("/OuterJoint")
@Slf4j
public class OuterJointPhoneController {

    @Autowired
    private big_data_phone_Repository foodRepository;

    //操作日志外连接口
    @Autowired
    LogInfoRepository logInfoRepository;
//    guide
    @GetMapping("/guide")
    public String guide(ModelMap map) {
        return "OuterJoint/list";
    }



        /*
        if(txtSearch == null || txtSearch.equals("")){
            foodPage = foodRepository.findAll(request);
            map.put("PhoneCountSum",foodPage.getTotalElements());
        }else{
            Integer count = foodRepository.count(txtSearch);
            List<big_data_phone> foodPages = foodRepository.findAllBigDataPhone(txtSearch,(page - 1)*size, size);
            foodPage = new PageImpl<big_data_phone>(foodPages, request,count);
            map.put("PhoneCountSum",count);
        }
        map.put("txtSearch", txtSearch);
        map.put("foodPage", foodPage);
        map.put("currentPage", page);
        map.put("size", size);
        return "big_data_phone/list";
    }*/
     @GetMapping("/logger")
    public String logger(@RequestParam(value = "page", defaultValue = "1") Integer page,
                         @RequestParam(value = "size", defaultValue = "15") Integer size,
                         ModelMap map) {
         PageRequest request = PageRequest.of(page - 1, size);
         Page<OperationLog> foodPage;
//         foodPage = logInfoRepository.findAll(request);
//         map.put("PhoneCountSum",foodPage.getTotalElements());
         Integer count = logInfoRepository.countNum();
         List<OperationLog> foodPages = logInfoRepository.findAll((page - 1)*size, size);
         foodPage = new PageImpl<OperationLog>(foodPages, request,count);
         map.put("PhoneCountSum",count);

         map.put("foodPage", foodPage);
         map.put("currentPage", page);
         map.put("size", size);
        return "logger/list";
    }

    //添加或更新 外部接口
    @GetMapping("/savePhone")
    @ResponseBody
    public String savePhone(@Valid big_data_phone_Req form,
                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "error";
        }
        big_data_phone productInfo = new big_data_phone();
        try{
            if (!StringUtils.isEmpty(form.getId())) {
                productInfo = foodRepository.findById(form.getId()).orElse(null);
            }
            BeanUtils.copyProperties(form, productInfo);
            foodRepository.save(productInfo);
        }catch (Exception e){
            log.error("添加手机号错误={}", e);
        }
        return "success";
    }

}
