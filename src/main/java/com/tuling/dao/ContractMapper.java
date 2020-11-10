package com.tuling.dao;

import com.tuling.entity.Contract;
import com.tuling.entity.ContractExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ContractMapper {
    long countByExample(ContractExample example);

    int deleteByExample(ContractExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Contract record);

    int insertSelective(Contract record);

    List<Contract> selectByExample(ContractExample example);

    Contract selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Contract record, @Param("example") ContractExample example);

    int updateByExample(@Param("record") Contract record, @Param("example") ContractExample example);

    int updateByPrimaryKeySelective(Contract record);

    int updateByPrimaryKey(Contract record);

    /**
     * 当前总条数
     * @return
     */
    @Select("select COUNT(*) from CONTRACT WHERE ID<>1")
    Integer selectSumCount();

    /**
     * 查询最大流水号
     * @param contNum
     * @return
     */
    Contract selectContractCountMax(String contNum);

    /**
     * 查询合同+对照编号
     * @param status
     * @return
     */
    List<Contract> selectContractAndIdMapper(String status);
}