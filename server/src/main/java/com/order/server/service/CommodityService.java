package com.order.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.server.util.DataFormatUtil;
import com.order.server.util.HttpServiceUtil;
import com.order.server.util.RedisUtil;
import com.order.server.util.ValidateUtil;
import com.storage.api.response.BaseResponse;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/***
 * @description: 分布式商城系统-下单系统： 使用OKHTTP调用仓储系统服务
 * @author pikav
 * @date 2020-3-15
 */

@Service
public class CommodityService {

    private static  final Logger log = LoggerFactory.getLogger(CommodityService.class);

    @Autowired
    private HttpServiceUtil httpServiceUtil;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String URL = "http://127.0.0.1:1818/storage/commodity/list";


    /**
     * @description: 查询仓储系统商品信息
     * @param pageNo
     * @param pageSize
     * @param search
     * @return
     * @author: pikav
     */
    public String commodityListByOkHttp(String pageNo, String pageSize, String search) throws Exception{
        String params = DataFormatUtil.paramsFormat(
                new String[] {"pageNo","pageSize","search"},
                new String[] {pageNo,pageSize,search});
        String response = httpServiceUtil.get(URL + params,null);
        BaseResponse baseResponse = objectMapper.readValue(response,BaseResponse.class);
        if (!ValidateUtil.isNullOrEmpty(baseResponse) && 0 == baseResponse.getCode()) {
            return objectMapper.writeValueAsString(baseResponse.getData());
        }
        return null;
    }

}
