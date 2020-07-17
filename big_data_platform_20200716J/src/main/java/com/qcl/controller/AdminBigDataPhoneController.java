package com.qcl.controller;

import com.qcl.bean.big_data_phone;
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
 * 更新时间：2020年7月14日12:29:26
 * 大数据手机号管理：增删改查 导入导出表格 根据手机号筛选数据
 */
@Controller
@RequestMapping("/big_data_phone")
@Slf4j
public class AdminBigDataPhoneController {

    @Autowired
    private big_data_phone_Repository foodRepository;

    /*
    * 列表
    * 分页查询 模糊查询 size每页展示多少条数据 txtSearch模糊手机号
    * */
    @GetMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "15") Integer size,
                       @RequestParam(value = "txtSearch",required = false) String txtSearch,
                       ModelMap map) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<big_data_phone> foodPage;
        if(txtSearch == null || txtSearch.equals("")){
            Integer count = foodRepository.countNum();
            List<big_data_phone> foodPages = foodRepository.findAll((page - 1)*size, size);
            foodPage = new PageImpl<big_data_phone>(foodPages, request,count);
            map.put("PhoneCountSum",count);
//            foodPage = foodRepository.findAll(request);
//            map.put("PhoneCountSum",foodPage.getTotalElements());
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
    }
//    //列表
//    @GetMapping("/list")
//    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
//                       @RequestParam(value = "size", defaultValue = "20") Integer size,
//                       ModelMap map) {
//        PageRequest request = PageRequest.of(page - 1, size);
//        Page<big_data_phone> foodPage = foodRepository.findAll(request);
//        map.put("foodPage", foodPage);
//        map.put("currentPage", page);
//        map.put("size", size);
//        return "big_data_phone/list";
//    }

    //删除某个数据
    @GetMapping("/remove")
    public String remove(@RequestParam(value = "Id", required = false) Integer Id,ModelMap map) {
        foodRepository.deleteById(Id);
        map.put("url", "/BigDataPhone/big_data_phone/list");
        return "zujian/success";
    }

    //菜品详情页
    @GetMapping("/index")
    public String index(@RequestParam(value = "Id", required = false) Integer Id,ModelMap map) {
        if(Id != null){
            big_data_phone food = foodRepository.findById(Id).orElse(null);
            map.put("food", food);
        }
        //查询所有的类目
//        List<Leimu> leimuList = leiMuRepository.findAll();
//        map.put("leimuList", leimuList);
        return "big_data_phone/index";
    }

    //添加或更新
    @PostMapping("/save")
    public String save(@Valid big_data_phone_Req form,
                       BindingResult bindingResult,
                       ModelMap map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/BigDataPhone/big_data_phone/index");
            return "zujian/error";
//            return "big_data_phone/list";
        }

        big_data_phone productInfo = new big_data_phone();
        try {
            //如果productId为空, 说明是新增
            if (!StringUtils.isEmpty(form.getId())) {
                productInfo = foodRepository.findById(form.getId()).orElse(null);
            }
            BeanUtils.copyProperties(form, productInfo);
            foodRepository.save(productInfo);
        } catch (Exception e) {
            log.error("添加手机号错误={}", e);
            map.put("msg", "添加手机号出错");
            map.put("url", "/BigDataPhone/big_data_phone/index");
            return "zujian/error";
        }

        map.put("url", "/BigDataPhone/big_data_phone/list");
        return "zujian/success";
//        return "/big_data_phone/list";
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
        try {
            //如果productId为空, 说明是新增
            if (!StringUtils.isEmpty(form.getId())) {
                productInfo = foodRepository.findById(form.getId()).orElse(null);
            }
            BeanUtils.copyProperties(form, productInfo);
            foodRepository.save(productInfo);
        } catch (Exception e) {
            log.error("添加手机号错误={}", e);
            return "error";
        }
        return "success";
    }

    //导出到excel
    @ResponseBody
    @GetMapping("/export")
    public String export(HttpServletResponse response,@RequestParam(value = "txtSearch",required = false) String txtSearch, ModelMap map) {
        String fileName = "手机号导出";
        String[] titles = {"名字", "手机号", "性别", "创建时间", "更新时间"};
//        如果搜索为空，则到处所有，如果有搜索项，导出搜索的数据
        List<big_data_phone> foodList;
        if(txtSearch == null || txtSearch.equals("")){
            foodList = foodRepository.findAll();
        }else{
            foodList = foodRepository.findAllBigDataPhoneExport(txtSearch);
        }
//        List<big_data_phone> foodList = foodRepository.findAll();
        if (foodList == null || foodList.size() < 1) {
            map.put("msg", "为空");
            map.put("url", "/BigDataPhone/big_data_phone/list");
            return "zujian/error";
        }
        int size = foodList.size();
        String[][] dataList = new String[size][titles.length];
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat( "yyyy-MM-dd ");
        for (int i = 0; i < size; i++) {
            big_data_phone food = foodList.get(i);
            dataList[i][0] = food.getName();
            dataList[i][1] = food.getPhone();
            dataList[i][2] = food.getSex();
            dataList[i][3] = formatter.format(food.getCreateTime());
            dataList[i][4] = formatter.format(food.getUpdateTime());
//            dataList[i][3] = food.getName();
//            dataList[i][1] = "" + food.getFoodPrice();
//            dataList[i][2] = "" + food.getFoodStock();//库存
//            dataList[i][3] = "" + food.getLeimuType();//菜品类目的type
//            dataList[i][4] = food.getFoodDesc();
//            dataList[i][5] = food.getFoodIcon();
        }

        try {
            ExcelExportUtils.createWorkbook(fileName, titles, dataList, response);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "导出excel失败");
            map.put("url", "/BigDataPhone/big_data_phone/list");
            return "zujian/error";
        }
        map.put("url", "/BigDataPhone/big_data_phone/list");
        return "zujian/success";
    }

    //excel导入网页
    @GetMapping("/excel")
    public String excel(ModelMap map) {
        return "big_data_phone/excel";
    }

    /*
     * 批量导入excel里的菜品(商品)到数据库
     * */
//    @RequestMapping("/uploadExcel")
//    public String uploadExcel(@RequestParam("file") MultipartFile file,
//                              ModelMap map) {
//        String name = file.getOriginalFilename();
//        if (name.length() < 6 || !name.substring(name.length() - 5).equals(".xlsx")) {
//            map.put("msg", "文件格式错误");
//            map.put("url", "/diancan/food/excel");
//            return "zujian/error";
//        }
//        List<Food> list;
//        try {
//            list = ExcelImportUtils.excelToFoodInfoList(file.getInputStream());
//            log.info("excel导入的list={}", list);
//            if (list == null || list.size() <= 0) {
//                map.put("msg", "导入失败");
//                map.put("url", "/diancan/food/excel");
//                return "zujian/error";
//            }
//            //excel的数据保存到数据库
//            try {
//                for (Food excel : list) {
//                    if (excel != null) {
//                        foodRepository.save(excel);
//                    }
//                }
//            } catch (Exception e) {
//                log.error("某一行存入数据库失败={}", e);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            map.put("msg", e.getMessage());
//            map.put("url", "/diancan/food/excel");
//            return "zujian/error";
//        }
//        map.put("url", "/diancan/food/list");
//        return "zujian/success";
//    }


    /*
     * 批量导入excel里的(商品)到数据库
     * */
    @RequestMapping("/uploadExcel")
    public String uploadExcel(@RequestParam("file") MultipartFile file,
                              ModelMap map) {
        String name = file.getOriginalFilename();
        if (name.length() < 6 || !name.substring(name.length() - 5).equals(".xlsx")) {
            map.put("msg", "文件格式错误");
            map.put("url", "/BigDataPhone/big_data_phone/excel");
            return "zujian/error";
        }
        List<big_data_phone> list;
        try {
            list = ExcelImportUtils.excelToPhoneInfoList(file.getInputStream());
            log.info("excel导入的list={}", list);
            if (list == null || list.size() <= 0) {
                map.put("msg", "导入失败");
                map.put("url", "/BigDataPhone/big_data_phone/excel");
                return "zujian/error";
            }
            //excel的数据保存到数据库
            try {
                for (big_data_phone excel : list) {
                    if (excel != null) {
                        foodRepository.save(excel);
                    }
                }
            } catch (Exception e) {
                log.error("某一行存入数据库失败={}", e);
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", e.getMessage());
            map.put("url", "/BigDataPhone/big_data_phone/excel");
            return "zujian/error";
        }
        map.put("url", "/BigDataPhone/big_data_phone/list");
        return "zujian/success";
    }

    //
//    //菜品上架
//    @RequestMapping("/on_sale")
//    public String onSale(@RequestParam("foodId") int foodId,
//                         ModelMap map) {
//        try {
//            Food food = foodRepository.findById(foodId).orElse(null);
//            if (food == null) {
//                throw new DianCanException(ResultEnum.PRODUCT_NOT_EXIST);
//            }
//            if (food.getFoodStatusEnum() == FoodStatusEnum.UP) {
//                throw new DianCanException(ResultEnum.PRODUCT_STATUS_ERROR);
//            }
//            food.setFoodStatus(FoodStatusEnum.UP.getCode());
//            foodRepository.save(food);
//        } catch (DianCanException e) {
//            map.put("msg", e.getMessage());
//            map.put("url", "/diancan/food/list");
//            return "zujian/error";
//        }
//
//        map.put("url", "/diancan/food/list");
//        return "zujian/success";
//    }
//
//    //菜品下架
//    @RequestMapping("/off_sale")
//    public String offSale(@RequestParam("foodId") int foodId,
//                          ModelMap map) {
//        try {
//            Food food = foodRepository.findById(foodId).orElse(null);
//            if (food == null) {
//                throw new DianCanException(ResultEnum.PRODUCT_NOT_EXIST);
//            }
//            if (food.getFoodStatusEnum() == FoodStatusEnum.DOWN) {
//                throw new DianCanException(ResultEnum.PRODUCT_STATUS_ERROR);
//            }
//            food.setFoodStatus(FoodStatusEnum.DOWN.getCode());
//            foodRepository.save(food);
//        } catch (DianCanException e) {
//            map.put("msg", e.getMessage());
//            map.put("url", "/diancan/food/list");
//            return "zujian/error";
//        }
//
//        map.put("url", "/diancan/food/list");
//        return "zujian/success";
//    }
//
//


}
