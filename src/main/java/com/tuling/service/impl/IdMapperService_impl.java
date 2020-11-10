package com.tuling.service.impl;

import com.tuling.dao.IdMappingMapper;
import com.tuling.entity.IdMapping;
import com.tuling.entity.IdMappingExample;
import com.tuling.service.IdMappingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public Integer updateByEnquireId(IdMapping idMapping) {
        //通过询价书修改编号对照状态
        IdMappingExample example = new IdMappingExample();
        example.createCriteria().andEnquireIdEqualTo(idMapping.getEnquireId());
        return idMappingMapper.updateByExampleSelective(idMapping,example);
    }

    @Override
    public IdMapping selectByIdMapingAll(IdMapping idMapping) {
        IdMappingExample example = new IdMappingExample();
        //需求计划序号!=null == 通过需求计划序号查询
        if(idMapping.getOrderId()!=null){
            example.createCriteria().andOrderIdEqualTo(idMapping.getOrderId());
        }
        //采购计划序号!=null == 通过采购计划序号查询
        if(idMapping.getStockId()!=null){
            example.createCriteria().andStockIdEqualTo(idMapping.getStockId());
        }
        //询价书序号!=null == 通过询价书序号查询
        if(idMapping.getEnquireId()!=null){
            example.createCriteria().andEnquireIdEqualTo(idMapping.getEnquireId());
        }
        //合同申请序号!=null == 通过合同申请序号查询
        if(idMapping.getContAppId()!=null){
            example.createCriteria().andContAppIdEqualTo(idMapping.getContAppId());
        }
        //报价序号!=null  == 通过报价序号查询
        if(idMapping.getQuoteId()!=null){
            example.createCriteria().andQuoteIdEqualTo(idMapping.getQuoteId());
        }
        //合同序号!=null == 通过合同序号查询
        if(idMapping.getContId()!=null){
            example.createCriteria().andContIdEqualTo(idMapping.getContId());
        }
        List<IdMapping> idMappings = idMappingMapper.selectByExample(example);
        return idMappings.get(0);
    }

    @Override
    public Integer updateByContrAppId(IdMapping idMapping) {
        //通过询价书修改编号对照状态
        IdMappingExample example = new IdMappingExample();
        example.createCriteria().andContAppIdEqualTo((long)idMapping.getContAppId());
        return idMappingMapper.updateByExampleSelective(idMapping,example);
    }

    @Override
    public Integer updateByIdContractId(IdMapping idMapping) {
        //通过询价书修改编号对照状态
        IdMappingExample example = new IdMappingExample();
        example.createCriteria().andContIdEqualTo(idMapping.getContId());
        return idMappingMapper.updateByExampleSelective(idMapping,example);
    }
}
