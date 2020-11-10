package com.tuling.controller;

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
import java.util.*;

/**
 * 询价书控制层
 */
@Controller
public class EnquireController {
    //注入询价书 业务层 EnquireService
    @Autowired
    private EnquireService enquireService;

    //注入采购计划 业务层 StockService
    @Autowired
    private StockService stockService;

    //注入 编号对照 业务层 IdMapperService
    @Autowired
    private IdMappingService idMappingService;

    //注入 需求计划 业务层 ordersService
    @Autowired
    private OrdersService ordersService;

    //注入 询价书详情 业务层 EnquireDetailService
    @Autowired
    private EnquireDetailService enquireDetailService;

    //注入报价书 业务层
    @Autowired
    private QuoteService quoteService;

    /**
     * 编辑询价书
     * @return
     */
    @RequestMapping("enquireEditor")
    public String enquireEditor(Model model,String stockNum){
        //自动生成询价书编号
        //当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        //调用查询流水号的方法
        Enquire byEnquireNumMax = enquireService.findByEnquireNumMax("300");
        //截取最后七位数
        long Num = Long.valueOf(byEnquireNumMax.getEnquireNum())+1;
        //询价书编号  100+当前日期+5位流水号
        String enquireNum = "300"+simpleDateFormat.format(date)+String.valueOf(Num).substring(11);
        //当前询价书自动生成编号
        model.addAttribute("enquireNum",enquireNum);

        //通过采购计划编号 查询需求计划
        Stock stockAndIdMapperAndOrders = stockService.findStockAndIdMapperAndOrders(stockNum);
        model.addAttribute("stockAndIdMapperAndOrders",stockAndIdMapperAndOrders);
        return "Enquire_bianzhi";
    }

    /**
     * 保存编辑的询价书
     * @param enquire
     * @param enquireDetail
     * @return
     */
    @RequestMapping("enquireEditorSave")
    @ResponseBody
    public String enquireEditorSave(Enquire enquire,EnquireDetail enquireDetail,String status,String endDatev,String bstartDatev,String bendDatev) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        enquire.setEndDate(format.parse(endDatev));
        enquireDetail.setEndDate(format.parse(bendDatev));
        enquireDetail.setStartDate(format.parse(bstartDatev));
        //添加询价书
        Integer insEnquire = enquireService.insertEnquire(enquire);
        if(insEnquire>0){
            //通过需求计划查询编号对照项
            IdMapping idMapping = idMappingService.selectByOrderId(enquireDetail.getOrderId().intValue());
            idMapping.setStatus(status);
            idMapping.setEnquireId(enquire.getId());
            //修改编号对照状态
            Integer updIdMap = idMappingService.updateById(idMapping);
            if(updIdMap>0){
                //查询需求计划信息
                Orders byIdOrders = ordersService.findByIdOrders(enquireDetail.getOrderId().intValue());
                enquireDetail.setEnquireId(enquire.getId());
                enquireDetail.setMaterialCode(byIdOrders.getMaterialCode());
                enquireDetail.setMaterialName(byIdOrders.getMaterialName());
                enquireDetail.setMeasureUnit(byIdOrders.getMeasureUnit());
                enquireDetail.setAmount(byIdOrders.getAmount());
                //添加询价书详情
                Integer integer = enquireDetailService.insertEnquireDetail(enquireDetail);
                if(integer>0){
                    return "1";
                }
            }
        }
        return "redirect:/queryandqueto/Project_list.html";
    }

    /**
     * 高级查询  查询所有询价书
     * @param curPage   当前页数
     * @param pageSize  当前页数数量
     * @param enquireName   当前询价书名称
     * @return
     */
    @RequestMapping("enquireIMapperAll")
    @ResponseBody
    public EasyUiDataGrid enquireIMapperAll(@RequestParam(defaultValue = "1") Integer curPage,@RequestParam(defaultValue = "3") Integer pageSize,String enquireName){
        EasyUiDataGrid enquireStatusNameAllPaging = enquireService.findEnquireStatusNameAllPaging(curPage, pageSize, enquireName,curPage+"");
        List<Object> list = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat();
        int i = 0;
        for (Object o :enquireStatusNameAllPaging.getRows()) {
            i++;
            Map<String,String> map = new HashMap<>();
            map.put("i",i+"");
            map.put("enquireId",((Enquire)o).getId().toString());
            map.put("enquireName",((Enquire)o).getEnquireName());
            map.put("endDate",((Enquire)o).getEndDate()!=null?format.format(((Enquire)o).getEndDate()):"");
            map.put("start","2020-11-3");
            map.put("status",((Enquire)o).getIdMapping().getStatus());
            map.put("stockType","公开求购");
            list.add(map);
        }
        enquireStatusNameAllPaging.setRows(list);
        return enquireStatusNameAllPaging;
    }

    /**
     * 查询询价书详情
     * @param id    询价书编号
     * @param model
     * @return
     */
    @RequestMapping("enquireByIdAll")
    public String enquireByIdAll(Integer id,Model model,String status){
        //查询询价书及对照编号表
        Enquire enquireByIdAndIdMapper = enquireService.findEnquireByIdAndIdMapper(id);
        String url = "";
        if(status.equals("1")){
            //查询需求计划项
            Orders byIdOrders = ordersService.findByIdOrders(enquireByIdAndIdMapper.getIdMapping().getOrderId().intValue());
            model.addAttribute("enquireByIdAndIdMapper",enquireByIdAndIdMapper);
            model.addAttribute("byIdOrders",byIdOrders);
            url = "Enquire_update";
        }else if(status.equals("2")){
            Enquire enquireByIdDetail = enquireService.findEnquireByIdDetail(id);
            model.addAttribute("enquireDetail",enquireByIdDetail.getEnquireDetail());
            model.addAttribute("enquireByIdAndIdMapper",enquireByIdAndIdMapper);
            url = "Enquiredetails";
        }else if(status.equals("3")){
            //揭示询价书详情
            Enquire enquireByIdDetail = enquireService.findEnquireByIdDetail(id);
            //查询询价书
            model.addAttribute("enquireByIdDetail",enquireByIdDetail);
            //查询报价书详情
            Quote quote = new Quote();
            quote.setEnquireId((long)id);
            Quote queryObjetQuoteAndQuoteDetail = quoteService.findQueryObjetQuoteAndQuoteDetail(quote);
            model.addAttribute("queryObjetQuoteAndQuoteDetail",queryObjetQuoteAndQuoteDetail);

            url = "Apply_xjsmx";
        }
        return url;
    }

    /**
     * 修改询价书  + 编号对照状态
     * @param enquire   询价书
     * @param enquireDetail
     * @param status
     * @return
     */
    @RequestMapping("enquireByidUpdate")
    public String enquireByidUpdate(Enquire enquire,EnquireDetail enquireDetail,String status){
        //修改询价书
        Integer integer = enquireService.updateByIdEnquire(enquire);
        if(integer>0){
            IdMapping idMapping = new IdMapping();
            idMapping.setEnquireId(enquire.getId());
            idMapping.setStatus(status);
            //修改状态
            idMappingService.updateByEnquireId(idMapping);
            return "ask1";
        }
        return "";
    }

    /**
     * 删除询价书
     * @param enquireId
     * @return
     */
    @RequestMapping("enquireByidDelete")
    @ResponseBody
    public String enquireByidDelete(Integer enquireId){
        //删除询价书详情
        Integer integer = enquireDetailService.deleteEnquireDetailByEnquireId(enquireId);
        if(integer>0){
            //查询编号对照信息
            Enquire enquireByIdAndIdMapper = enquireService.findEnquireByIdAndIdMapper(enquireId);
            IdMapping idMapping = new IdMapping();
            idMapping.setId(enquireByIdAndIdMapper.getIdMapping().getId());
            idMapping.setStatus("C001-60");
            idMapping.setEnquireId((long)1);
            //修改编号对照表   //考虑流程回退
            Integer integer1 = idMappingService.updateById(idMapping);
            if(integer1>0){
                //删除询价书
                Integer integer2 = enquireService.deleteByIdEnquire(enquireId);
                return integer2+"";
            }
        }
        return "";
    }
}
