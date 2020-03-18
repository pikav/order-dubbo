package com.order.server.service;

import com.order.model.entity.MyOrder;
import com.order.model.mapper.MyOrderMapper;
import com.order.server.util.ConstantsUtil;
import com.order.server.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 * @description: 分布式商城系统-下单系统： 下单服务
 * @author pikav
 * @date 2020-3-15
 */

@Service
public class MyOrderService {

    @Autowired
    private MyOrderMapper myOrderMapper;

    /**
     * @description: 查询我的所有订单(购物车)
     * @return
     * @author: pikav
     */
    public List<MyOrder> searchMyOrder() throws Exception{
        List<MyOrder> myOrderList = myOrderMapper.myOrderList();
        if (!ValidateUtil.isNullOrEmpty(myOrderList)) {
            return myOrderList;
        }
        return null;
    }

    /**
     * @description: 下单(加入购物车)
     * @param: commodityCode
     * @return
     * @author: pikav
     */
    public void addShopCatrs(String commodityCode) throws Exception {
        Integer sum = myOrderMapper.sumShopCarts(commodityCode);
        if(sum == 0) {
            MyOrder myOrder = new MyOrder(ConstantsUtil.getID(),commodityCode,1);
            myOrderMapper.addShopCart(myOrder);
        } else {
            myOrderMapper.updateShopCarts(commodityCode);
        }
    }

}
