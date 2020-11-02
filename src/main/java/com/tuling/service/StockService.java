package com.tuling.service;

import com.tuling.entity.EasyUiDataGrid;
import com.tuling.entity.Orders;
import com.tuling.entity.Stock;

import java.util.List;

/**
 * 采购计划接口
 */
public interface StockService {
    /**
     * 查询流水号
     * @param orderNum
     * @return
     */
    public Stock findByStockNum(String orderNum);

    /**
     * 添加采购信息
     * @param stock 采购对象
     * @return
     */
    public Integer insertStock(Stock stock);

    /**
     * 查询所有采购计划  分页+模糊查询
     * @param curPage   当前页数
     * @param pageSize  当前页数条数
     * @return
     */
    public EasyUiDataGrid findStockPageAll(Integer curPage, Integer pageSize,String status);

    /**
     * 查询采购计划 详情信息
     * @param stockNum  采购计划编号
     * @return
     */
    public Stock findStockAndIdMapperAndOrders(String stockNum);

    /**
     * 审核报批  通过采购计划编号 修改 编号对照状态
     * @param status    编号对照状态
     * @param stockNum  采购计划编号
     * @return
     */
    public Integer updateStockByStockNumIdmaStatus(String status,String stockNum);

    /**
     * 通过采购计划序号修改
     * @param stock
     * @return
     */
    public Integer updateStockById(Stock stock);
}
