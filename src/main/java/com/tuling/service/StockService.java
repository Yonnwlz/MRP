package com.tuling.service;

import com.tuling.entity.Orders;
import com.tuling.entity.Stock;

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
}
