package com.tuling.service.impl;

import com.tuling.dao.ContractDetailMapper;
import com.tuling.entity.ContractDetail;
import com.tuling.entity.ContractDetailExample;
import com.tuling.service.ContractDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 合同详情接口实现类
 */
@Service
public class ContractDetailService_impl implements ContractDetailService {
    //注入 合同详情mapper
    @Resource
    private ContractDetailMapper contractDetailMapper;

    @Override
    public Integer insertContractDetail(ContractDetail contractDetail) {
        return contractDetailMapper.insertSelective(contractDetail);
    }

    @Override
    public ContractDetail findContractDetailById(Integer contdetaId) {
        ContractDetailExample example = new ContractDetailExample();
        example.createCriteria().andContIdEqualTo((long)contdetaId);
        return contractDetailMapper.selectByExample(example).get(0);
    }
}
