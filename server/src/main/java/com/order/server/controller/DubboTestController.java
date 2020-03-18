package com.order.server.controller;

import com.storage.api.response.BaseResponse;
import com.storage.api.service.IDubboTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DubboTestController {

    private static final String URL_MAPPING = "/test";

    @Autowired
    private IDubboTestService testService;

    @GetMapping(value = URL_MAPPING)
    public String commodityList(String pageNo){
        BaseResponse baseResponse = testService.dubboTest(pageNo);
        return "main";
    }

}
