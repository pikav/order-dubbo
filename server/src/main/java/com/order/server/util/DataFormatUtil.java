package com.order.server.util;

/***
 * @description: 分布式商城系统-下单系统： 数据格式化工具类
 * @author pikav
 * @date 2020-3-15
 */

public class DataFormatUtil {

    /**
     * @description: 把形参转行成url参数： ?a=v1&b=v2
     * @param: keys
     * @param: values
     * @return
     * @author pikav
     */
    public static String paramsFormat(String[] keys, String[] values){
        if (keys.length <= 0 || values.length <= 0) return "";
        StringBuffer urlParams = new StringBuffer("?");
        for (int i = 0;i < keys.length;i++){
            if(urlParams.length() > 1) urlParams.append("&");
            urlParams.append(keys[i]).append("=").append(values[i]);
        }
        return urlParams.toString();
    }
}
