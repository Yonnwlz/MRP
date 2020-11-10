package com.tuling.controller;

import com.tuling.dao.QuoteMapper;
import com.tuling.entity.*;
import com.tuling.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 供应商控制层
 */

@Controller
public class SupplierController {
    //注入 编号对照service
    @Autowired
    private IdMappingService idMappingService;

    //注入报价书service
    @Autowired
    private QuoteService quoteService;

    //注入 询价书service
    @Autowired
    private EnquireService enquireService;

    //注入需求计划service
    @Autowired
    private OrdersService ordersService;

    //注入 报价书详情service
    @Autowired
    private QuoteDetailService quoteDetailService;

    /**
     * 查询所有询价书
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("Lookupallinquiries")
    @ResponseBody
    public EasyUiDataGrid quoteAll(@RequestParam(defaultValue = "1") Integer curPage, @RequestParam(defaultValue = "3") Integer pageSize){
        //查询所有询价书
        EasyUiDataGrid enquireStatusNameAllPaging = enquireService.findEnquireStatusNameAllPaging(curPage, pageSize, null,null);

        ArrayList<Map> list = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        int i =0;
        for (Object o:enquireStatusNameAllPaging.getRows()) {
            i++;
            Map<String,String> map = new HashMap<>();
            map.put("id",((Enquire)o).getId()+"");
            map.put("i",i+"");
            map.put("enquireName",((Enquire) o).getEnquireName());
            map.put("linkman",((Enquire) o).getLinkman());
            map.put("endDate",((Enquire) o).getEndDate()!=null?simpleDateFormat.format(((Enquire) o).getEndDate()):"");
            map.put("company",((Enquire) o).getCompany());
            map.put("status","公开报价");
            list.add(map);
        }
        enquireStatusNameAllPaging.setRows(list);
        return enquireStatusNameAllPaging;
    };

    /**
     * 添加报价书+报价书详情
     * @param quote 报价书
     * @param endDatev  结束到货期
     * @param queDatev  报价有效期
     * @param orderId   需求计划序号
     * @return
     */
    @RequestMapping("eganToOffer")
    public String eganToOffer(Quote quote,String endDatev,QuoteDetail quoteDetail,String queDatev,String startDatev,Integer orderId) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //生成报价书编号
        //当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        //调用查询流水号的方法
        Quote byQuoteNumMax = quoteService.findByQuoteNumMax("500");
        //截取最后七位数
        Long Num = Long.valueOf(byQuoteNumMax.getQuoteNum())+1;
        //需求计划编号  100+当前日期+5位流水号
        String quoteNum = "500"+simpleDateFormat.format(date)+String.valueOf(Num).substring(11);
        quote.setQuoteNum(quoteNum);
        try {
            quote.setEndDate(format.parse(endDatev));
            quote.setQueDate(format.parse(queDatev));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        quote.setStatus("B001-1");
        /**
         * 供应商信息未添加
         */
        //生成报价书
        Integer integer = quoteService.insertQuote(quote);
        if(integer>0){
            //添加报价书序号 到 编号对照表中
            Orders byIdOrdersAndIdMapper = ordersService.findByIdOrdersAndIdMapper(orderId);
            IdMapping idMapping = byIdOrdersAndIdMapper.getIdMapping();
            idMapping.setQuoteId(quote.getId());
            //修改对照编号状态
            idMappingService.updateById(idMapping);
            quoteDetail.setQuoteId(quote.getId());
            //查询需求计划项
            Orders byIdOrders = ordersService.findByIdOrders(orderId);
            quoteDetail.setMaterialCode(byIdOrders.getMaterialCode());
            quoteDetail.setMaterialName(byIdOrders.getMaterialName());
            quoteDetail.setMeasureUnit(byIdOrders.getMeasureUnit());
            quoteDetail.setStartDate(simpleDateFormat.parse(startDatev));
            quoteDetail.setEndDate(simpleDateFormat.parse(endDatev));
            Integer integer1 = quoteDetailService.insertQuoteDetail(quoteDetail);
            if(integer1>0){
                System.out.println("报价书添加成功！");
                return "Order_wbxjfa_list";
            }
        }
        return "Order_wbxjfa_list";
    }
}
