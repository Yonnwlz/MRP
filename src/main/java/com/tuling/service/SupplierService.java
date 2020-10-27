package com.tuling.service;

import com.tuling.entity.Supplier;

import java.util.List;

/**
 * 供应商基本信息接口
 */
public interface SupplierService {
    /**
     * 查询所有供应商信息
     * @return
     */
    public List<Supplier> findSupplierAll();

    /**
     * 通过供应商编号查询供应商信息
     * @param supplierId
     * @return
     */
    public Supplier findBySupplierid(Integer supplierId);
}
