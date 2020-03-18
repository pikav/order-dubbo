package com.order.model.mapper;

import com.order.model.entity.MyOrder;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MyOrderMapper extends Mapper<MyOrder> {

    List<MyOrder> myOrderList();

    Integer sumShopCarts(String commodityCode);

    void addShopCart(MyOrder myOrder);

    void updateShopCarts(String commodityCode);

    List<MyOrder> myList();

}