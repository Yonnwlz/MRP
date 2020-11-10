package com.tuling.service;

import com.tuling.entity.StockSupplier;

/**
 * 采购计划已选供应商接口
 */
public interface StockSupplierService {

    /**
     * 添加采购计划已选供应商接口
     * @param stockSupplier 采购计划已选供应商对象
     * @return
     */
    public Integer insertStockSupplier(StockSupplier stockSupplier);

    /**
     * 供应商序号查询
     * @param stockId
     * @return
     */
    public StockSupplier findByStockSupplier(Integer stockId);
}
