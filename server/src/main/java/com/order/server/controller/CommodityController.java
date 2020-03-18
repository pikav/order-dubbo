package com.order.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.server.service.CommodityService;
import com.order.server.util.ConstantsUtil;
import com.order.server.util.DataFormatUtil;
import com.order.server.util.HttpServiceUtil;
import com.order.server.util.RedisUtil;
import com.storage.api.response.BaseResponse;
import com.storage.api.service.IDubboCommodityService;
import com.storage.model.entity.Commodity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommodityController {

    private static  final Logger log = LoggerFactory.getLogger(CommodityController.class);

    private static final String URL_MAPPING = "/commodity";

    private static final String URL_OKHTTP = "/okhttp/commodity";

    @Autowired
    private IDubboCommodityService dubboCommodityService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CommodityService commodityService;

    @Resource
    private RedisUtil redisUtil;

    @GetMapping(value = URL_MAPPING)
    public String commodityList(String pageNo, String pageSize, String serach, Model model){
        try {
            if (redisUtil.hasKey(ConstantsUtil.CACHE_KEY_COMMODITY + pageNo)) {
                model.addAttribute("commodityList",redisUtil.get(ConstantsUtil.CACHE_KEY_COMMODITY + pageNo));
            } else {
                BaseResponse baseResponse = dubboCommodityService.commodityPageListBySearch(pageNo,pageSize,serach);
                String data = objectMapper.writeValueAsString(baseResponse.getData());
                model.addAttribute("commodityList", data);
                redisUtil.set(ConstantsUtil.CACHE_KEY_COMMODITY + pageNo, data, ConstantsUtil.EXPIRE_TIME);
            }
        } catch (JsonProcessingException e) {
            log.info("查询商品失败",e.fillInStackTrace());
            e.printStackTrace();
        }
        return "main";
    }

    @GetMapping(value = URL_OKHTTP)
    public String commodityListByOkHttp(String pageNo, String pageSize, String search, Model model){
        try {
            model.addAttribute("commodityList", commodityService.commodityListByOkHttp(pageNo,pageSize,search));
        } catch (Exception e) {
            log.info("查询商品信息失败-byOKHTTP：", e.fillInStackTrace());
            e.printStackTrace();
        }
        return "main";
    }

}
