package com.tuling.service;

import com.tuling.entity.ContractApply;
import com.tuling.entity.EasyUiDataGrid;

/**
 * 申请合同接口
 */
public interface ContractApplyService {

    /**
     * 生成申请合同
     * @param contractApply
     * @return
     */
    public Integer insertContractApply(ContractApply contractApply);

    /**
     * 查询最大流水号
     * @param ContAppNum  模糊查询 申请合同编号
     * @return
     */
    public ContractApply findContractApplyCount(String ContAppNum);

    /**
     * 修改合同申请信息
     * @param contractApply
     * @return
     */
    public Integer updateContractApply(ContractApply contractApply);

    /**
     * 查询合同申请信息项
     * @param contId  合同申请序号
     * @return
     */
    public ContractApply findByIdContractApply(Integer contId);

    /**
     * 查询合同申请 + 编号对照
     * @param status
     * @return
     */
    public EasyUiDataGrid findContractApplyAndIdMapperAll(Integer curPage,Integer pageSize,String status);
}
