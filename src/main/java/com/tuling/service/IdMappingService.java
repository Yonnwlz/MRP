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
}
