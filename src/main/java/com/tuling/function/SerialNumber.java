package com.tuling.function;

import com.tuling.entity.Orders;
import com.tuling.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SerialNumber {
    //注入 需求计划业务层 ordersService
    @Autowired
    private OrdersService ordersService;

    /**
     * 自动生成编号
     * @param nu
     * @return
     */
    public String editorNu(String nu){
        //当前时间
        Date date = new Date();
        //当前日期
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat years = new SimpleDateFormat("yyyy");


        //调用查询流水号的方法
        Orders byOrderNum = ordersService.findByOrderNum(nu+years.format(date));
        //截取最后六位数
        int orderNum = Integer.parseInt(byOrderNum.getOrderNum().substring(10))+1;
        //需求计划编号  100+当前日期+5位流水号
        String ordernum = "100"+simpleDateFormat.format(date)+String.valueOf(orderNum).substring(1);

        return ordernum;
    }
}
