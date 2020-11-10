package com.tuling.controller;

import com.tuling.entity.*;
import com.tuling.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 审批控制层
 */
@Controller
public class AuditController {
    //注入 stockService 采购计划业务层
    @Autowired
    private StockService stockService;

    //注入  询价书Service
    @Autowired
    private EnquireService enquireService;

    //注入  编号对照Servier
    @Autowired
    private IdMappingService idMappingService;

    //注入 合同申请servier
    @Autowired
    private ContractApplyService contractApplyService;

    //注入 采购计划已选供应商Service
    @Autowired
    private StockSupplierService stockSupplierService;

    //注入 供应商service
    @Autowired
    private SupplierService supplierService;

    //注入 报价表Service
    @Autowired
    private QuoteService quoteService;



    /**
     * 查询所有需审核的采购计划
     * @param curPage  当前页数
     * @param pageSize  当前显示页数
     * @param status    编号对照状态
     * @return
     */
    @RequestMapping("stockAuditPlannerAll")
    @ResponseBody
    public EasyUiDataGrid stockAuditPlannerAll(@RequestParam(defaultValue = "1") Integer curPage, @RequestParam(defaultValue = "10") Integer pageSize,String status){
        //调用easyuidatagrid 对象
        EasyUiDataGrid stockPageAll = stockService.findStockPageAll(curPage, pageSize,status);
        //创建list
        List<Object> objects = new ArrayList<Object>();
        //时间格式化
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int i = 0;
        //遍历EasyUiDataGrid rows属性
        for (Object o :stockPageAll.getRows()) {
            i++;
            //创建map
            Map<String, String> map = new HashMap<String, String>();
            map.put("number", String.valueOf(i));
            map.put("stockNum",((Stock)o).getStockNum());
            map.put("stockId",((Stock)o).getId()+"");
            map.put("stockName", ((Stock) o).getStockName());
//            //查询合同申请表
//            IdMapping idma = new IdMapping();
//            idma.setStockId(((Stock) o).getId());
//            IdMapping idMapping = idMappingService.selectByIdMapingAll(idma);
//            ContractApply byIdContractApply = contractApplyService.findByIdContractApply(idMapping.getContAppId().intValue());
            map.put("progress","待审");


            map.put("budget",((Stock) o).getBudget()+"");
            map.put("startDate",((Stock) o).getStartDate()==null?"":simpleDateFormat.format(((Stock) o).getStartDate()));
            map.put("endDate",((Stock) o).getEndDate()==null?"":simpleDateFormat.format(((Stock) o).getEndDate()));
//            //查询采购计划+编号对照表
//            Stock stockAndIdMapperAndOrders = stockService.findStockAndIdMapperAndOrders(((Stock) o).getStockNum());
//            //查询询价书
//            Enquire enquireById = enquireService.findEnquireById(stockAndIdMapperAndOrders.getIdMapping().getEnquireId().intValue());
//            map.put("enquireName",enquireById.getEnquireName());
            objects.add(map);
        }
        stockPageAll.setRows(objects);
        return stockPageAll;
    }


    /**
     * 采购详情  审批详情
     * @param stockNum
     * @param approval 详情类型
     * @return
     */
    @RequestMapping("stockSelectAndIdMppOrdersAll")
    public ModelAndView stockSelectAndIdMppOrdersAll(String stockNum,String approval){
        ModelAndView mv = new ModelAndView();
        //根据采购计划编号查询详情
        Stock stockAndIdMapperAndOrders = stockService.findStockAndIdMapperAndOrders(stockNum);
        mv.addObject("stockIdMapperOrders",stockAndIdMapperAndOrders);
        if(approval!=null){
            if(approval.equals("1")){
                //跳转采购计划详情
                mv.setViewName("Apply_list_do");
            }else if(approval.equals("2")){
                //跳转采购计划审核详情
                mv.setViewName("Apply_caiwushenpi");
            }else if(approval.equals("3")){
                //查询编号对照项
                IdMapping idMapping = new IdMapping();
                idMapping.setStockId(stockAndIdMapperAndOrders.getId());
                IdMapping idMapping1 = idMappingService.selectByIdMapingAll(idMapping);
                //查询询价书
                Enquire enquireByIdDetail = enquireService.findEnquireByIdDetail(idMapping1.getEnquireId().intValue());
                mv.addObject("enquireByIdDetail",enquireByIdDetail);
                //查询供应商
                StockSupplier byStockSupplier = stockSupplierService.findByStockSupplier(stockAndIdMapperAndOrders.getId().intValue());
                Supplier bySupplierid = supplierService.findBySupplierid(byStockSupplier.getSupplierId().intValue());
                mv.addObject("bySupplierid",bySupplierid);
                //查询合同申请
                ContractApply byIdContractApply = contractApplyService.findByIdContractApply(idMapping1.getContAppId().intValue());
                mv.addObject("byIdContractApply",byIdContractApply);

                //查询报价
                Quote quote = new Quote();
                quote.setId(idMapping1.getQuoteId());
                Quote queryObjetQuoteAndQuoteDetail = quoteService.findQueryObjetQuoteAndQuoteDetail(quote);
                mv.addObject("queryObjetQuoteAndQuoteDetail",queryObjetQuoteAndQuoteDetail);

                queryObjetQuoteAndQuoteDetail.getQuoteDetail().getTotalPrice();

                //跳转合同申请审核详情
                mv.setViewName("Apply_jihuashenpi");
            }
        }
        return mv;
    }

    /**
     * 审核采购计划
     * @param stockNum 采购计划编号
     * @param status   编号对照状态
     * @return
     */
    @RequestMapping("stockExamination")
    public String stockExamination(String stockNum,String status){
        //调用通过采购计划编号修改对照编号状态
        Integer integer = stockService.updateStockByStockNumIdmaStatus(null,status,stockNum);
        if(integer>0){
            return "redirect:/contractmanager/Apply_daishencaiwu.html";
        }
        return "";
    }

    /**
     * 合同申请审核
     * @param contractApply
     * @param leadDatev
     * @return
     */
    @RequestMapping("toApplyForTheContractYes")
    @ResponseBody
    public String toApplyForTheContractYes(ContractApply contractApply,String leadDatev) throws ParseException {
        IdMapping im = new IdMapping();
        im.setContAppId(contractApply.getId());
        IdMapping idMapping = idMappingService.selectByIdMapingAll(im);
        //C001-160  == 合同审核
        if(idMapping.getStatus().equals("C001-160")){
            IdMapping idMapping1 = new IdMapping();
            idMapping1.setContId(idMapping.getContId());
            idMapping1.setStatus("C001-170");
            Integer integer = idMappingService.updateByIdContractId(idMapping1);
            if(integer>0){
                return integer+"";
            }
        }
        //合同申请审核
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        contractApply.setLeadDate(format.parse(leadDatev));
        Integer integer = contractApplyService.updateContractApply(contractApply);
        return integer+"";
    }
}
