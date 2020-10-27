package com.tuling.service.impl;

import com.tuling.dao.SupplierMapper;
import com.tuling.entity.Supplier;
import com.tuling.service.SupplierService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 供应商基本信息实现类
 */
@Service
public class SupplierService_impl implements SupplierService {
    //注入  SupplierMapper 供应商基本mapper
    @Resource
    private SupplierMapper supplierMapper;

    @Override
    public List<Supplier> findSupplierAll() {
        //查询所有供应商基本信息
        return supplierMapper.selectByExample(null);
    }

    @Override
    public Supplier findBySupplierid(Integer supplierId) {
        //通过供应商编号查询供应商信息
        return supplierMapper.selectByPrimaryKey((long)supplierId);
    }
}
