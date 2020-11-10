package com.tuling.dao;

import com.tuling.entity.Orders;
import com.tuling.entity.OrdersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.core.annotation.Order;

public interface OrdersMapper {
    long countByExample(OrdersExample example);

    int deleteByExample(OrdersExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Orders record);

    int insertSelective(Orders record);

    List<Orders> selectByExample(OrdersExample example);

    Orders selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    /**
     * 查询最大流水号
     * @param num
     * @return
     */
    Orders selectByCount(@Param("num")String num);

    /**
     * 需求计划 编号对照  一对一查询+分页查询
     * @param curPage       当前页数
     * @param pageSize      当前页数条数
     * @return
     */
    List<Orders> selectOrderOneIdMapperPaging(Integer curPage,Integer pageSize,String ordernm,String status);

    /**
     * 查询当前总条数
     * @return
     */
    @Select("SELECT count(*) from ORDERS o INNER JOIN ID_MAPPING i on o.id=i.ORDER_ID")
    Integer selectOrderCount();

    /**
     * 通过需求计划序号查询项  需求计划+编号对照
     * @param oid
     * @return
     */
    Orders selectOrderByIdAndIdMapper(Integer oid);
}