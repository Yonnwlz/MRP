package com.tuling.service.impl;

import com.tuling.dao.StockSupplierMapper;
import com.tuling.entity.StockSupplier;
import com.tuling.service.StockSupplierService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 采购计划已选供应商接口实现类
 */
@Service
public class StockSupplerService_impl implements StockSupplierService {
    //注入 采购计划已选供应商mapper
    @Resource
    private StockSupplierMapper stockSupplierMapper;

    @Override
    public Integer insertStockSupplier(StockSupplier stockSupplier) {
        //添加采购计划已选供应商
        return stockSupplierMapper.insertSelective(stockSupplier);
    }
}
