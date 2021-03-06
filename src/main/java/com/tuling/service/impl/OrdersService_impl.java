package com.tuling.service.impl;

import com.tuling.dao.OrdersMapper;
import com.tuling.entity.EasyUiDataGrid;
import com.tuling.entity.Orders;
import com.tuling.entity.OrdersExample;
import com.tuling.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public EasyUiDataGrid findOrderOneIdMapper(Integer curPage, Integer pageSize,String ordernm,String status) {
        EasyUiDataGrid eay = new EasyUiDataGrid();
        //需求计划 编号对照 一对一查询 + 分页查询
        List<Orders> orders = ordersMapper.selectOrderOneIdMapperPaging((curPage-1)*pageSize,pageSize,ordernm,status);
        eay.setRows(orders);
        eay.setTotal(ordersMapper.selectOrderCount());
        return eay;
    }

    @Override
    public Orders findByIdOrders(Integer ordersId) {
        //通过需求计划序号查询需求计划项
        return ordersMapper.selectByPrimaryKey((long)ordersId);
    }

    @Override
    public Integer updateOrderById(Orders orders) {
        //通过需求计划序号修改项
        return ordersMapper.updateByPrimaryKeySelective(orders);
    }

    @Override
    public Orders findByIdOrdersAndIdMapper(Integer ordersId) {
        //通过需求计划序号查询信息  需求计划+编号对照
        return ordersMapper.selectOrderByIdAndIdMapper(ordersId);
    }
}
