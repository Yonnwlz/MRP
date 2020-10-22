package com.tuling.service;

import com.tuling.entity.Orders;

import java.util.List;

/**
 * 需求计划接口
 */
public interface OrdersService {

    /**
     * 添加需求计划
     * @param orders
     * @return
     */
    public Integer insertOrders(Orders orders);

    /**
     * 查询流水号
     * @param orderNum
     * @return
     */
    public Orders findByOrderNum(String orderNum);
}
