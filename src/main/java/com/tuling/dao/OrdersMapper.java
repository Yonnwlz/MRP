package com.tuling.dao;

import com.tuling.entity.Orders;
import com.tuling.entity.OrdersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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

    Orders selectByCount(@Param("num")String num);
}