package com.tuling.service;

import com.tuling.entity.EasyUiDataGrid;
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

    /**
     * 需求计划+编号对照  一对一查询 + 分页查询
     * @param curPage
     * @param pageSize
     * @return
     */
    public EasyUiDataGrid findOrderOneIdMapper(Integer curPage, Integer pageSize,String ordernm,String status);

    /**
     * 通过id查询需求计划信息
     * @param ordersId  需求序号
     * @return
     */
    public Orders findByIdOrders(Integer ordersId);

    /**
     * 通过需求计划序号修改项
     * @param orders
     * @return
     */
    public Integer updateOrderById(Orders orders);

    /**
     * 通过需求计划序号查询信息  需求计划+编号对照
     * @param ordersId  需求计划序号
     * @return
     */
    public Orders findByIdOrdersAndIdMapper(Integer ordersId);
}
