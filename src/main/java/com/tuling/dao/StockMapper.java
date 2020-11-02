package com.tuling.dao;

import com.tuling.entity.Orders;
import com.tuling.entity.Stock;
import com.tuling.entity.StockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface StockMapper {
    long countByExample(StockExample example);

    int deleteByExample(StockExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Stock record);

    int insertSelective(Stock record);

    List<Stock> selectByExample(StockExample example);

    Stock selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Stock record, @Param("example") StockExample example);

    int updateByExample(@Param("record") Stock record, @Param("example") StockExample example);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);

    /**
     * 查询最大流水号
     * @param num
     * @return
     */
    Stock selectByCount(@Param("num")String num);

    /**
     * 当前总条数
     * @return
     */
    @Select("select COUNT(*) from STOCK WHERE ID<>1")
    Integer selectSumCount();

    /**
     * 查询所有采购计划 + 分页 + 模糊查询
     * @param curPage  当前页数
     * @param pageSize  当前页数条数
     * @param status    编号对照状态
     * @return
     */
    List<Stock> selectStockAll(Integer curPage,Integer pageSize,String status);

    /**
     * 三表联查  采购计划 + 编号对照 + 需求计划 表
     * @param stockNum  采购计划编号
     * @return
     */
    Stock selectStockAndIdMapperAndOrdersAll(String stockNum);
}