package com.order.server.util;

import java.util.List;

/**
 * @Description: 数据有效性校验校验工具类
 * @author: pikav
 * @date 2020-3-15
 */
public class ValidateUtil {

    /**
     * @Description: 判断对象是否为空
     * @param: objs
     * @return boolean
     * @author: pikav
     */
    public static boolean isNullOrEmpty(Object... objs){
        List temp = null;

        for(Object obj : objs) {
            if(obj == null || "".equals(obj) || "null".equals(obj)) return true;

            if(obj instanceof String && "".equals(((String)obj).trim())){
                return false;
            }

            if(obj instanceof List){
                temp = (List) obj;
                if(temp.isEmpty() || temp.size() == 0 || (temp.size() > 0 && temp.get(0) == null)){
                    return  true;
                }
            }
        }
        return false;
    }

}
