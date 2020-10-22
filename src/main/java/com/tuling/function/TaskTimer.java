package com.tuling.function;

import com.tuling.entity.Orders;
import com.tuling.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 实现功能
 */
@Component
public class TaskTimer {
    //注入 需求计划业务层 ordersService
    @Autowired
    private OrdersService ordersService;
    //每年10月22号1点1分
    @Scheduled(cron = "0 1 1 1 1 ?")
    public void myTimes(){
        //创建需求计划对象
        Orders orders = new Orders();
        //当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy");
        //调用查询流水号的方法    根据100+当前年份来查询
        Orders byOrderNum = ordersService.findByOrderNum("100"+simpleDateFormat2.format(date));
        //截取最后六位数
        int orderNum = Integer.parseInt(byOrderNum.getOrderNum().substring(10))+1;
        //需求计划编号  100+当前日期+5位流水号
        String ordernum = "100"+simpleDateFormat1.format(date)+"00001";
        orders.setOrderNum(ordernum);
        ordersService.insertOrders(orders);
    }

    //每五秒钟 添加一条数据  流失号清空
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void my(){
//        //创建需求计划对象
//        Orders orders = new Orders();
//        //当前时间
//        Date date = new Date();
//        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMdd");
//        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy");
//        //调用查询流水号的方法    根据100+当前年份来查询
//        Orders byOrderNum = ordersService.findByOrderNum("100"+simpleDateFormat2.format(date));
//        //截取最后六位数
//        int orderNum = Integer.parseInt(byOrderNum.getOrderNum().substring(10))+1;
//        //需求计划编号  100+当前日期+5位流水号
//        String ordernum = "100"+simpleDateFormat1.format(date)+"00001";
//        orders.setOrderNum(ordernum);
//        ordersService.insertOrders(orders);
//    }
}
