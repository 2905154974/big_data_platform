package com.qcl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by qcl on 2019-04-12
 * 微信：2501902696
 * desc:验证项目能不能运行起来
 */
@Controller
public class HelloController {

    @GetMapping("/")
    public String hello() {
        System.out.println("项目可以正常的跑起来了");
//        return "moban/index";
        return "您好，我是牛亚博";
    }

}
