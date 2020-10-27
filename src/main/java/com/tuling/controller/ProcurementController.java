package com.tuling.controller;

import com.tuling.entity.*;
import com.tuling.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

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

    //注入 供应商基本信息 业务层  supplierService;
    @Autowired
    private SupplierService supplierService;

    //注入 物资信息 业务层  materialService;
    @Autowired
    private MaterialService materialService;

    //注入 供应商对应的商品 业务层  materialService;
    @Autowired
    private SuppMaterialService suppMaterialService;


    //物资是否相对应的供应商
    boolean fign = true;
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

    /**
     * 编写采购计划
     * @param ordersid
     * @return
     */
    @RequestMapping("stockByOrder")
    public ModelAndView stockByOrder(String ordersid){
        ModelAndView mv = new ModelAndView("bianzhicaigoujihua");
        //存储需求计划内容
        ArrayList<Orders> orlist = new ArrayList<Orders>();
        //切割前端传的 序号
        String[] split = ordersid.split(",");

        //供应商编号
        Map<Long,Long> supplierIdList = new HashMap<Long,Long>();
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

            //通过需求计划物资编号查询物资序号
            Material byMaterialNum = materialService.findByMaterialNum(byIdOrders.getMaterialCode());
            //通过物资序号查询相对应的供应商信息
            List<SuppMaterial> bySuppMaterial = suppMaterialService.findBySuppMaterial(byMaterialNum.getId().intValue());
            //遍历供应商对应商品
            for (SuppMaterial su:bySuppMaterial) {
                if(su==null){
                    fign = false;
                }else {
                    supplierIdList.put(su.getSupplierId(),su.getSupplierId());
                }
            }
        }
        //当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        //调用查询流水号的方法
        Stock stockNum = stockService.findByStockNum("200");
        //截取最后七位数
        int orderNum = Integer.parseInt(stockNum.getStockNum().substring(9))+1;
        //采购计划编号  100+当前日期+5位流水号
        String stocNum = "200"+simpleDateFormat.format(date)+String.valueOf(orderNum).substring(2);
        //mv 存入 需要添加的需求计划==orlist  存入自动生成采购计划序号==ordernum  存入所有供应商基本信息==suplist
        mv.addObject("orlist",orlist);
        mv.addObject("stockNum",stocNum);
        //把查询出的供应商通过集合存储
        ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
        //判断是否是相对应的供应商
        if(fign && supplierIdList.size()>0){
            for (Map.Entry<Long,Long> entry :supplierIdList.entrySet()) {
                //调用查询供应商名称的方法
                Supplier bySupplierid = supplierService.findBySupplierid(entry.getValue().intValue());
                suppliers.add(bySupplierid);
            }
        }
        mv.addObject("suppliers",suppliers);
        mv.addObject("suplist",supplierService.findSupplierAll());
        return mv;
    };

    /**
     * 添加采购计划 和 修改对照编号
     * @return
     */
    @RequestMapping("stockInsertUpdIdMapper")
    public String stockInsert(){

        return null;
    }
}
