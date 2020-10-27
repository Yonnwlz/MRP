package com.tuling.service.impl;

import com.tuling.dao.StockMapper;
import com.tuling.entity.Stock;
import com.tuling.service.StockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 采购计划接口实现类
 */
@Service
public class StockService_impl implements StockService {
    //注入 采购计划mapper  StockMapper
    @Resource
    private StockMapper stockMapper;


    @Override
    public Stock findByStockNum(String orderNum) {
        return stockMapper.selectByCount(orderNum);
    }
}
