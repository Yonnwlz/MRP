package com.tuling.service.impl;

import com.tuling.dao.OrdersMapper;
import com.tuling.entity.Orders;
import com.tuling.entity.OrdersExample;
import com.tuling.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 需求计划接口实现类
 */
@Service
public class OrdersService_impl implements OrdersService {
    //注入 需求计划 ordersMapper
    @Resource
    private OrdersMapper ordersMapper;

    @Override
    public Integer insertOrders(Orders orders) {
        //添加需求计划
        return ordersMapper.insertSelective(orders);
    }

    @Override
    public Orders findByOrderNum(String orderNum) {
        //查询流水号
        return ordersMapper.selectByCount(orderNum);
    }
}
