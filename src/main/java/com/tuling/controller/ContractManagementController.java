package com.tuling.controller;

import com.github.pagehelper.PageHelper;
import com.sun.org.apache.xpath.internal.operations.Quo;
import com.tuling.dao.IdMappingMapper;
import com.tuling.entity.*;
import com.tuling.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 合同管理控制层
 */
@Controller
public class ContractManagementController {
    //注入询价书service
    @Autowired
    private EnquireService enquireService;

    //注入 报价书service
    @Autowired
    private QuoteService quoteService;

    //注入 编号对照service
    @Autowired
    private IdMappingService idMappingService;

    //注入  采购计划对应供应商service
    @Autowired
    private StockSupplierService stockSupplierService;

    //注入  供应商service
    @Autowired
    private SupplierService supplierService;

    //注入  合同申请service
    @Autowired
    private ContractApplyService contractApplyService;

    //注入 合同service
    @Autowired
    private ContractService contractService;

    //注入 合同详情service
    @Autowired
    private ContractDetailService contractDetailService;
    /**
     * 查询所有询价书项
     * @return
     */
    @RequestMapping("offerToRevealAll")
    @ResponseBody
    public EasyUiDataGrid offerToRevealAll(@RequestParam(defaultValue = "1") Integer curPage, @RequestParam(defaultValue = "3") Integer pageSize, String  enquireName,String status){
        //查询所有询价书
        EasyUiDataGrid enquireStatusNameAllPaging = enquireService.findEnquireStatusNameAllPaging(curPage, pageSize, enquireName, status);
        //时间格式化
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        List<Map> list = new ArrayList<>();
        //序号
        int i = 0;
        for (Object o:enquireStatusNameAllPaging.getRows()) {
            i++;
            Map<String,String> map = new HashMap<>();
            map.put("i",i+"");
            map.put("enquireName",((Enquire)o).getEnquireName());
            map.put("endDate",format.format(((Enquire) o).getEndDate()));
            Quote quote = new Quote();
            quote.setEnquireId(((Enquire) o).getId());
            //查询报价书
            Quote queryObjetQuoteAndQuoteDetail = quoteService.findQueryObjetQuoteAndQuoteDetail(quote);
            map.put("sumMoney",queryObjetQuoteAndQuoteDetail.getQuoteDetail().getTotalPrice()+"");
            map.put("enquireId",((Enquire) o).getId()+"");
            list.add(map);
        }
        enquireStatusNameAllPaging.setRows(list);
        return enquireStatusNameAllPaging;
    }

    /**
     * 编辑合同详情
     * @param enquireId  询价书序号
     * @return
     */
    @RequestMapping("editContractDetails")
    public String editContractDetails(Integer enquireId, Model model){
        //查询询价书
        Enquire enquireById = enquireService.findEnquireById(enquireId);
        model.addAttribute("enquireById",enquireById);

        //查询报价书详情
        Quote quote = new Quote();
        quote.setEnquireId((long)enquireId);
        Quote queryObjetQuoteAndQuoteDetail = quoteService.findQueryObjetQuoteAndQuoteDetail(quote);
        model.addAttribute("queryObjetQuoteAndQuoteDetail",queryObjetQuoteAndQuoteDetail);

        //查询供应商
        IdMapping idMapping = new IdMapping();
        idMapping.setEnquireId((long)enquireId);
        IdMapping idMapping1 = idMappingService.selectByIdMapingAll(idMapping);
        //供应商序号查询
        StockSupplier byStockSupplier = stockSupplierService.findByStockSupplier(idMapping1.getStockId().intValue());
        //查询供应商信息
        Supplier bySupplierid = supplierService.findBySupplierid(byStockSupplier.getSupplierId().intValue());
        model.addAttribute("bySupplierid",bySupplierid);
        return "Apply_bianji";
    }

    /**
     * 合同申请保存
      * @param contractApply
     * @return
     */
    @RequestMapping("peserveTheMergerContract")
    @ResponseBody
    public String preserveTheMergerContract(ContractApply contractApply,Integer enquireId,String status){
        //当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        //调用查询流水号的方法
        ContractApply contApp = contractApplyService.findContractApplyCount("400");
        //截取最后七位数
        Long contNum = Long.valueOf(contApp.getContAppNum())+1;
        //需求计划编号  100+当前日期+5位流水号
        String contAppNum = "400"+simpleDateFormat.format(date)+String.valueOf(contNum).substring(11);
        //赋值
        contractApply.setContAppNum(contAppNum);
        contractApply.setStatQuote("1");
        contractApply.setLowQuote(contractApply.getTopQuote());
        BigDecimal bd=new BigDecimal(contractApply.getTopQuote());
        contractApply.setAllSumPrice(bd);
        //添加合同申请
        Integer insertContra = contractApplyService.insertContractApply(contractApply);
        if(insertContra>0){
            //保存合同申请序号
            IdMapping idMapping = new IdMapping();
            idMapping.setEnquireId((long)enquireId);
            idMapping.setContAppId(contractApply.getId());
            if(status!=null){
                idMapping.setStatus("C001-105");
            }else{
                idMapping.setStatus("C001-110");
            }
            Integer integer = idMappingService.updateByEnquireId(idMapping);
            if(integer>0){
                return "success";
            }
        }
        return "";
    }

    /**
     * 查询可编辑的合同
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("prepareTheContract")
    @ResponseBody
    public EasyUiDataGrid prepareTheContract(@RequestParam(defaultValue = "1") Integer curPage,@RequestParam(defaultValue = "4") Integer pageSize,String status){
        EasyUiDataGrid contractApplyAndIdMapperAll = contractApplyService.findContractApplyAndIdMapperAll(curPage, pageSize, "C001-110");
        List<Map> list = new ArrayList<>();
        int i =0;
        for (Object o:contractApplyAndIdMapperAll.getRows()) {
            i++;
            Map<String,String> map = new HashMap<>();
            map.put("i",i+"");
            map.put("id",((ContractApply)o).getId()+"");
            map.put("contAppNum",((ContractApply)o).getContAppNum());
            map.put("allSumPrice",((ContractApply) o).getAllSumPrice()+"");
            map.put("status",((ContractApply) o).getIdMapping().getStatus());
            list.add(map);
        }
        contractApplyAndIdMapperAll.setRows(list);
        return contractApplyAndIdMapperAll;
    }

    /**
     * 编制合同
     * @param contId
     * @return
     */
    @RequestMapping("prepareContractCont")
    public ModelAndView prepareContractCont(Integer contId){
        ModelAndView mv = new ModelAndView("bianzhihetong");
        //当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        //调用查询流水号的方法
        Contract contractCountMax = contractService.findContractCountMax("600");
        //截取最后七位数
        Long contNum = Long.valueOf(contractCountMax.getContNum())+1;
        //需求计划编号  100+当前日期+5位流水号
        String contAppNum = "400"+simpleDateFormat.format(date)+String.valueOf(contNum).substring(11);
        mv.addObject("contAppNum",contAppNum);

        //查询合同申请详情
        ContractApply byIdContractApply = contractApplyService.findByIdContractApply(contId);
        mv.addObject("byIdContractApply",byIdContractApply);
        //编号对照项
        IdMapping im = new IdMapping();
        im.setContAppId((long)contId);
        IdMapping idMapping = idMappingService.selectByIdMapingAll(im);
        //查询卖家  报价表
        Quote qu = new Quote();
        qu.setId((long)idMapping.getQuoteId());
        Quote queryObjetQuoteAndQuoteDetail = quoteService.findQueryObjetQuoteAndQuoteDetail(qu);
        mv.addObject("queryObjetQuoteAndQuoteDetail",queryObjetQuoteAndQuoteDetail);
        //查询询价表 买家
        Enquire enquireByIdDetail = enquireService.findEnquireByIdDetail((idMapping.getEnquireId()).intValue());
        mv.addObject("enquireByIdDetail",enquireByIdDetail);
        //查询供应商
        //供应商序号查询
        StockSupplier byStockSupplier = stockSupplierService.findByStockSupplier(idMapping.getStockId().intValue());
        //查询供应商信息
        Supplier bySupplierid = supplierService.findBySupplierid(byStockSupplier.getSupplierId().intValue());
        mv.addObject("bySupplierid",bySupplierid);
        return mv;
    }

    /**
     * 录入合同
     * @param contract
     * @param contractDetail
     * @return
     */
    @RequestMapping("luron")
    @ResponseBody
    public String luron(Contract contract,ContractDetail contractDetail,Integer contAppId){
        //生成合同
        Integer integer = contractService.insertContract(contract);
        if(integer>0){
            //生成合同详情
            contractDetail.setContId(contract.getId());
            Integer integer1 = contractDetailService.insertContractDetail(contractDetail);
            //修改编号对照编号
            if (integer1>0) {
                IdMapping im = new IdMapping();
                im.setContAppId((long)contAppId);
                im.setContId(contract.getId());
                im.setStatus("C001-150");
                Integer integer2 = idMappingService.updateByContrAppId(im);
                if(integer2>0){
                    return integer2+"";
                }
            }
        }
        return "";
    }

    /**
     * 查询所有需要确定的合同
     * @param curPage
     * @param pageSize
     * @param status
     * @return
     */
    @RequestMapping("enterheontract")
    @ResponseBody
    public EasyUiDataGrid enterheontract(@RequestParam(defaultValue = "1") Integer curPage,@RequestParam(defaultValue = "4") Integer pageSize,String status){
        EasyUiDataGrid contractAndIdMapper = contractService.findContractAndIdMapper(curPage, pageSize, status);
        List<Map> list = new ArrayList<>();
        int i = 0;
        for (Object o:contractAndIdMapper.getRows()) {
            i++;
            Map<String,String> map = new HashMap<>();
            map.put("i",i+"");
            map.put("id",((Contract)o).getId()+"");
            map.put("contNum",((Contract)o).getContNum());
            map.put("contPrice",((Contract) o).getContPrice()+"");
            map.put("seller",((Contract) o).getSeller());
            list.add(map);
        }
        contractAndIdMapper.setRows(list);
        return contractAndIdMapper;
    }

    /**
     * 查询合同详情
     * @param contId
     * @param model
     * @return
     */
    @RequestMapping("contrDetails")
    public String  contrDetails(Integer contId,Model model){
        //查询合同
        Contract contractById = contractService.findContractById(contId);
        model.addAttribute("contractById",contractById);
        //查询合同详情
        ContractDetail contractDetailById = contractDetailService.findContractDetailById(contractById.getId().intValue());
        model.addAttribute("contractDetailById",contractDetailById);

        System.out.println(contractById.toString());
        System.out.println(contractDetailById.toString());
        return "Apply_list22";
    }

    /**
     * 录入合同审核
     */
    @RequestMapping("inputContractReview")
    @ResponseBody
    public String inputContractReview(Integer contId,String status){
        //通过合同序号修改状态
        IdMapping im = new IdMapping();
        im.setContId((long)contId);
        im.setStatus(status);
        Integer integer = idMappingService.updateByIdContractId(im);
        return integer+"";
    }
}
