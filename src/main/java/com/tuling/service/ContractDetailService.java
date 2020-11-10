package com.tuling.service;

import com.tuling.entity.ContractDetail;

/**
 * 合同详情接口
 */
public interface ContractDetailService {

    /**
     * 生成合同详情
     * @param contractDetail
     * @return
     */
    public Integer insertContractDetail(ContractDetail contractDetail);

    /**
     * 根据合同详情序号查询
     * @param contdetaId
     * @return
     */
    public ContractDetail findContractDetailById(Integer contdetaId);
}
