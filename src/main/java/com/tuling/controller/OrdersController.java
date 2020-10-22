package com.tuling.controller;

import com.tuling.entity.IdMapping;
import com.tuling.entity.Material;
import com.tuling.entity.Orders;
import com.tuling.service.IdMappingService;
import com.tuling.service.MaterialService;
import com.tuling.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 需求计划控制层
 */
@Controller
public class OrdersController {
    //注入 需求计划业务层 ordersService
    @Autowired
    private OrdersService ordersService;

    //注入 物资业务层 materialService
    @Autowired
    private MaterialService materialService;

    //注入 编号对照业务层 IdMappingService
    @Autowired
    private IdMappingService idMappingService;

    @RequestMapping("effe")
    public ModelAndView addOrder(String orderId){
        ModelAndView mv = new ModelAndView("Order_newform");
        //将当前选择的物资序号 进行存储
        ArrayList<Orders> ordList = new ArrayList<Orders>();

        //将前端数据进行切割
        String[] split = orderId.split(",");
        //遍历 split
        for (String a: split) {
            //查询物资方法
            Material byIdMaterial = materialService.findByIdMaterial(Integer.parseInt(a));
            //创建需求计划对象
            Orders orders = new Orders();
            //当前时间
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            //调用查询流水号的方法
            Orders byOrderNum = ordersService.findByOrderNum("100");
            //截取最后七位数
            int orderNum = Integer.parseInt(byOrderNum.getOrderNum().substring(9))+1;
            //需求计划编号  100+当前日期+5位流水号
            String ordernum = "100"+simpleDateFormat.format(date)+String.valueOf(orderNum).substring(2);
            //赋值需求计划编号
            orders.setOrderNum(ordernum);
            //赋值物资编号
            orders.setMaterialCode(byIdMaterial.getMaterialNum());
            //赋值物资名称
            orders.setMaterialName(byIdMaterial.getMaterialName());
            //赋值物资记量单位
            orders.setMeasureUnit(byIdMaterial.getMaterialUnit());
            //需求计划添加
            Integer integer = ordersService.insertOrders(orders);
            ordList.add(orders);
            //需求计划添加成功后  添加编号对照信息
            if(integer>0){
                //创建编号对照对象
                IdMapping idMapping = new IdMapping();
                //赋值需求计划序号
                idMapping.setOrderId(orders.getId());
                //赋值状态
                idMapping.setStatus("C001-10");
                idMappingService.insertIdMapping(idMapping);
            }
        }
        mv.addObject("ordList",ordList);
        return mv;
    }
}
