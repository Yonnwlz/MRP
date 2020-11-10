package com.tuling.service.impl;

import com.tuling.dao.IdMappingMapper;
import com.tuling.dao.StockMapper;
import com.tuling.entity.*;
import com.tuling.service.StockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 采购计划接口实现类
 */
@Service
public class StockService_impl implements StockService {
    //注入 采购计划mapper  StockMapper
    @Resource
    private StockMapper stockMapper;

    //注入 编号对照mapper idMappingMapper
    @Resource
    private IdMappingMapper idMappingMapper;


    @Override
    public Stock findByStockNum(String orderNum) {
        //查询最大采购计划流水号
        return stockMapper.selectByCount(orderNum);
    }

    @Override
    public Integer insertStock(Stock stock) {
        //添加采购计划
        return stockMapper.insertSelective(stock);
    }

    @Override
    public EasyUiDataGrid findStockPageAll(Integer curPage, Integer pageSize,String status) {
        //创建easyuiDatagrid 对象
        EasyUiDataGrid eas = new EasyUiDataGrid();
        //查询当前总条数
        Integer integer = stockMapper.selectSumCount();
        //查询所有采购计划项
        List<Stock> stocks = stockMapper.selectStockAll((curPage - 1) * pageSize, pageSize,status);
        eas.setTotal(integer);
        eas.setRows(stocks);
        return eas;
    }

    @Override
    public Stock findStockAndIdMapperAndOrders(String stockNum) {
        //查询采购计划 详情信息
        return stockMapper.selectStockAndIdMapperAndOrdersAll(stockNum);
    }

    @Override
    public Integer updateStockByStockNumIdmaStatus(Stock stock,String status, String stockNum) {
        //通过采购编号
        StockExample example = new StockExample();
        example.createCriteria().andStockNumEqualTo(stockNum);
        //查询是否有编号对照项
        List<Stock> stocks = stockMapper.selectByExample(example);
        if(stocks.size()>0){
            System.out.println(stocks.toString());
//            if(stock!=null){
//                stock.setId(stocks.get(0).getId());
//                //添加当前采购计划下达时间
//                stockMapper.updateByPrimaryKeySelective(stock);
//            }
            IdMapping idMapping = new IdMapping();
            idMapping.setStatus(status);
            //通过采购计划序号 修改 编号对照状态
            IdMappingExample idMappingExample = new IdMappingExample();
            idMappingExample.createCriteria().andStockIdEqualTo(stocks.get(0).getId());
            int i = idMappingMapper.updateByExampleSelective(idMapping,idMappingExample);
            if(i>0){
                return i;
            }
        }
        return 0;
    }

    @Override
    public Integer updateStockById(Stock stock) {
        //通过采购计划序号修改
        return stockMapper.updateByPrimaryKeySelective(stock);
    }
}
