package com.tuling.dao;

import com.tuling.entity.Enquire;
import com.tuling.entity.EnquireExample;
import java.util.List;

import com.tuling.entity.Orders;
import org.apache.ibatis.annotations.Param;

public interface EnquireMapper {
    long countByExample(EnquireExample example);

    int deleteByExample(EnquireExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Enquire record);

    int insertSelective(Enquire record);

    List<Enquire> selectByExample(EnquireExample example);

    Enquire selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Enquire record, @Param("example") EnquireExample example);

    int updateByExample(@Param("record") Enquire record, @Param("example") EnquireExample example);

    int updateByPrimaryKeySelective(Enquire record);

    int updateByPrimaryKey(Enquire record);

    /**
     * 查询最大流水号
     * @param num
     * @return
     */
    Enquire selectByCount(@Param("num")String num);

    /**
     * 查询所有询价书  + 模糊查询  + 分页
     * @param enquire   询价书对象 属性用于条件
     * @return
     */
    List<Enquire> selectEnquireAndIdMapper(String enquireName);


    /**
     * 通过询价书序号查询询价书信息
     * @param enquireId 询价书序号
     * @return
     */
    public Enquire selectEnquireByIdAndIdMapper(Integer enquireId);
}