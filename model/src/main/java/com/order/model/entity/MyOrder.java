package com.order.model.entity;

import javax.persistence.*;
import com.storage.model.entity.Commodity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Table(name = "t_sys_myorder")
public class MyOrder implements Serializable {
    @Id
    private String id;

    @Column(name = "commodityCode")
    private String commoditycode;

    private Integer number;

    private Commodity commodity;

    public MyOrder() { }

    public MyOrder(String id, String commoditycode, Integer number) {
        this.id = id;
        this.commoditycode = commoditycode;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommoditycode() {
        return commoditycode;
    }

    public void setCommoditycode(String commoditycode) {
        this.commoditycode = commoditycode;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }
}