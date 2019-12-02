package com.delicacy.grape.shardingsphere.domain;

import java.util.Date;

/**
 * 描述:t_order表的实体类
 * @version  1.0
 * @author:  yutao
 * @创建时间: 2019-10-29
 */
public class Order {
    private Long orderId;

    private Long userId;

    private String name;

    private String type;

    private Date createTime;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}