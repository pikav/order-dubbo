package com.order.server.util;

/***
 * @description: 分布式商城系统-下单系统： 常量工具类
 * @author pikav
 * @date 2020-3-15
 */

public class ConstantsUtil {

    public static final String SUCCESS = "成功";

    public static final String FAIL = "失败";

    public static final String SUCCESS_T = "T";

    public static final String FAIL_F = "F";

    // redis中存储的过期时间60s
    public static final int EXPIRE_TIME = 60;

    public static final String CACHE_KEY_COMMODITY = "commodity";

    /**
     * @description: 创建一个36位的ID号
     * @return
     * @author pikav
     */
    public static String getID(){
        return java.util.UUID.randomUUID().toString();
    }

}
