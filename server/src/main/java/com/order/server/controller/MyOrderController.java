package com.order.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.order.model.entity.MyOrder;
import com.order.server.service.MyOrderService;
import com.order.server.util.ConstantsUtil;
import com.order.server.util.ValidateUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyOrderController {

    private static  final Logger log = LoggerFactory.getLogger(MyOrderController.class);

    private static final String URL_MAPPING = "/myOrder";

    @Autowired
    private MyOrderService myOrderService;

    @Autowired
    private ObjectMapper objectMapper ;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping(value = URL_MAPPING)
    public String myOrderList() {
        try {
            List<MyOrder> myOrderList = myOrderService.searchMyOrder();
            if(!ValidateUtil.isNullOrEmpty(myOrderList)){
                return objectMapper.writeValueAsString(myOrderList);
            }
        } catch (Exception e) {
            log.info("查询购物车失败", e.fillInStackTrace());
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = URL_MAPPING)
    public void addShopCarts(String commodityCode){
        kafkaTemplate.send("shopCatrs", commodityCode);
    }

    @KafkaListener(topics = "shopCatrs")
    public String listen(ConsumerRecord<?, ?> record) {
        log.info("topic = {}, offset = {}, value = {} \n", record.topic(), record.offset(), record.value());
        String result = ConstantsUtil.SUCCESS_T;
        try {
            myOrderService.addShopCatrs(record.value().toString());
        } catch (Exception e) {
            log.info("加入购物车失败", e.fillInStackTrace());
            result = ConstantsUtil.FAIL_F;
            e.printStackTrace();
        }
        return result;
    }

}
