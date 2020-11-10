package com.tuling.dao;

import com.tuling.entity.ContractApply;
import com.tuling.entity.ContractApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ContractApplyMapper {
    long countByExample(ContractApplyExample example);

    int deleteByExample(ContractApplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ContractApply record);

    int insertSelective(ContractApply record);

    List<ContractApply> selectByExample(ContractApplyExample example);

    ContractApply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ContractApply record, @Param("example") ContractApplyExample example);

    int updateByExample(@Param("record") ContractApply record, @Param("example") ContractApplyExample example);

    int updateByPrimaryKeySelective(ContractApply record);

    int updateByPrimaryKey(ContractApply record);

    /**
     * 当前总条数
     * @return
     */
    @Select("select COUNT(*) from CONTRACT_APPLY WHERE ID<>1")
    Integer selectSumCount();

    /**
     * 查询最大流水号
     * @param contAppNum
     * @return
     */
    ContractApply selectContractApplyCount(String contAppNum);

    /**
     * 查询合同申请+编号对照
     * @param status
     * @return
     */
    List<ContractApply> selectContractApplyAndIdMapper(String status);
}