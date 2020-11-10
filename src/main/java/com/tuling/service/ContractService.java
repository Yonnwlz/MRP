package com.tuling.service;

import com.tuling.entity.Contract;
import com.tuling.entity.EasyUiDataGrid;

/**
 * 合同接口
 */
public interface ContractService {

    /**
     * 添加合同
     * @param contract
     * @return
     */
    public Integer insertContract(Contract contract);

    /**
     * 查询总条数
     * @return
     */
    public Integer findContractSumCount();

    /**
     * 查询最大流水号
     * @param contNum
     * @return
     */
    public Contract findContractCountMax(String contNum);

    /**
     * 分页查询 合同+对照编号项
     * @param curPage
     * @param pageSize
     * @param status
     * @return
     */
    public EasyUiDataGrid findContractAndIdMapper(Integer curPage,Integer pageSize,String status);

    /**
     * 根据合同序号查询 合同项
     * @param contId
     * @return
     */
    public Contract findContractById(Integer contId);

}
