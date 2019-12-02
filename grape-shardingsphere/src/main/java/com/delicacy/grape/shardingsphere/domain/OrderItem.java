package com.delicacy.grape.shardingsphere.domain;

/**
 * 描述:t_order_item表的实体类
 * @version  1.0
 * @author:  yutao
 * @创建时间: 2019-10-29
 */
public class OrderItem {
    private Long orderItemId;

    private Long orderId;

    private Integer userId;

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}