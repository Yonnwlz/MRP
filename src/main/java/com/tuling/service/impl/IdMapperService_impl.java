package com.tuling.service.impl;

import com.tuling.dao.IdMappingMapper;
import com.tuling.entity.IdMapping;
import com.tuling.entity.IdMappingExample;
import com.tuling.service.IdMappingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 实现编号对照接口类
 */
@Service
public class IdMapperService_impl implements IdMappingService {
    //注入 编号对照 IdMapperMapper 层
    @Resource
    private IdMappingMapper idMappingMapper;

    @Override
    public Integer insertIdMapping(IdMapping idMapping) {
        //添加一项 编号对照信息
        return idMappingMapper.insertSelective(idMapping);
    }

    @Override
    public Integer updateById(IdMapping idMapping) {
        //通过编号对照id修改 相对应的信息
        return idMappingMapper.updateByPrimaryKeySelective(idMapping);
    }

    @Override
    public IdMapping selectByOrderId(Integer orderId) {
        //通过需求计划序号 查询对照信息
        IdMappingExample example = new IdMappingExample();
        example.createCriteria().andOrderIdEqualTo((long)orderId);
        return idMappingMapper.selectByExample(example).get(0);
    }
}
