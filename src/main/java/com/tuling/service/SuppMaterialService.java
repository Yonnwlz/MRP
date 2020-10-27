package com.tuling.service;

import com.tuling.entity.SuppMaterial;

import java.util.List;

/**
 * 供应商对应商品接口
 */
public interface SuppMaterialService {
    /**
     * 根据商品序号查询相对应的供应商
     * @param materialId
     * @return
     */
    public List<SuppMaterial> findBySuppMaterial(Integer materialId);
}
