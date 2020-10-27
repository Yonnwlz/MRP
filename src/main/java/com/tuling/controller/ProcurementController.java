package com.tuling.controller;

import com.tuling.entity.EasyUiDataGrid;
import com.tuling.entity.Orders;
import com.tuling.entity.Stock;
import com.tuling.service.OrdersService;
import com.tuling.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 采购计划控制层
 */

@Controller
public class ProcurementController {
    //注入 需求计划 ordersService 业务层
    @Autowired
    private OrdersService ordersService;

    //注入 采购计划 stockService 业务层
    @Autowired
    private StockService stockService;
    /**
     * 查询所有已确定的计划
     * @param status 采购编号  C00-20已确定
     * @return
     */
    @RequestMapping("stockNotAll")
    @ResponseBody
    public EasyUiDataGrid OserSer(@RequestParam(defaultValue = "C001-20") String status){
        EasyUiDataGrid e = ordersService.findOrderOneIdMapper(1, 5, null,status);
        return e;
    }

    @RequestMapping("stockByOrder")
    public ModelAndView stockByOrder(String ordersid){
        ModelAndView mv = new ModelAndView("bianzhicaigoujihua");
        //存储需求计划内容
        ArrayList<Orders> orlist = new ArrayList<Orders>();
        //切割前端传的 序号
        String[] split = ordersid.split(",");
        //遍历序号
        for (String o:split) {
            //查询相对应的需求计划项
            Orders byIdOrders = ordersService.findByIdOrders(Integer.parseInt(o));
            //数量
            String amount = byIdOrders.getAmount();
            //价格
            BigDecimal unitPrice = byIdOrders.getUnitPrice();
            //计算数量和价格的总和
            byIdOrders.setSumPrice(BigDecimal.valueOf((int)Integer.parseInt(amount)).multiply(unitPrice));
            orlist.add(byIdOrders);
        }
        //当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        //调用查询流水号的方法
        Stock stockNum = stockService.findByStockNum("200");
        //截取最后七位数
        int orderNum = Integer.parseInt(stockNum.getStockNum().substring(9))+1;
        //采购计划编号  100+当前日期+5位流水号
        String ordernum = "200"+simpleDateFormat.format(date)+String.valueOf(orderNum).substring(2);
        //mv 存入 需要添加的需求计划  存入自动生成采购计划序号
        mv.addObject("orlist",orlist);
        mv.addObject("ordernum",ordernum);
        return mv;
    };
}
