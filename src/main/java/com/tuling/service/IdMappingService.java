package com.tuling.service;

import com.tuling.entity.IdMapping;

/**
 * 编号对照接口
 */
public interface IdMappingService {

    /**
     * 添加编号对照信息
     * @param idMapping
     * @return
     */
    public Integer insertIdMapping(IdMapping idMapping);

    /**
     * 通过编号对照id  修改相对应的信息项
     * @param idMapping
     * @return
     */
    public Integer updateById(IdMapping idMapping);

    /**
     * 通过需求计划序号  查询对照信息
     * @param orderId 需求计划序号
     * @return
     */
    public IdMapping selectByOrderId(Integer orderId);

    /**
     * 通过询价书序号修改状态
     * @param idMapping
     * @return
     */
    public Integer updateByEnquireId(IdMapping idMapping);
}
