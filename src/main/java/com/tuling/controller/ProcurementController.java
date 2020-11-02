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

    //注入 编号对照 业务层  idMappingService;
    @Autowired
    private IdMappingService idMappingService;

    //注入 采购计划已选供应商 业务层  stockSupplierService;
    @Autowired
    private StockSupplierService stockSupplierService;

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
     * 添加采购计划 + 修改对照编号 + 选择供应商
     * @param stock     采购计划对象
     * @param orderid   需求计划序号
     * @param suppid    供应商序号
     * @return
     */
    @RequestMapping("stockInsertUpdIdMapper")
    public String stockInsert(Stock stock,Integer orderid,String suppid){
        //添加采购计划
        Integer integer = stockService.insertStock(stock);
        if(integer>0){
            //调用 需求计划序号 查询对照信息
            IdMapping idMapping = idMappingService.selectByOrderId(orderid);
            idMapping.setStockId(stock.getId());
            idMapping.setStatus("C001-30");
            //通过对照信息序号 修改状态+采购计划
            Integer upd = idMappingService.updateById(idMapping);
            if(upd>0){
                //遍历供应商ID
                String[] split = suppid.split(",");
                for (String s:split) {
                    //创建采购计划已选供应商
                    StockSupplier stockSupplier = new StockSupplier();
                    stockSupplier.setStockId(stock.getId());
                    stockSupplier.setSupplierId((long)Integer.parseInt(s));
                    //添加采购计划已选供应商
                    Integer insertStockSupplier = stockSupplierService.insertStockSupplier(stockSupplier);
                    if(insertStockSupplier>0){
                        return "Project_list";
                    }
                }
            }
        }
        return "bianzhicaigoujihua";
    }

    /**
     * 查询所有采购计划 + 分页 + 模糊查询  + 采购计划下达 + 未通过审批采购计划
     * @param curPage
     * @param pageSize
     * @param status       编号对照 状态条件
     * @return
     */
    @RequestMapping("stockSelectAll")
    @ResponseBody
    public EasyUiDataGrid stockSelectAll(@RequestParam(defaultValue = "1") Integer curPage,@RequestParam(defaultValue = "10") Integer pageSize,String status){
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
            Map<String,String> map = new HashMap<String, String>();
            map.put("stockNum",((Stock)o).getStockNum());
            map.put("number",String.valueOf(i));
            map.put("stockName",((Stock) o).getStockName());
            map.put("submitDate",((Stock) o).getSubmitDate()==null?"":simpleDateFormat.format(((Stock) o).getSubmitDate()));
            map.put("progress","采购计划未下达");
            map.put("stockType","制造中心采购公开求购");
            map.put("status",((Stock) o).getIdMapping().getStatus());
            map.put("endDate",((Stock) o).getEndDate()==null?"":simpleDateFormat.format(((Stock) o).getEndDate()));
            map.put("enquire","");
            //status!=null   采购计划下达 + 未通过审批采购计划
            if(status!=null){
                //采购计划下达
                if(status.equals("C001-50")){
                    map.put("progress","采购计划未下达");
                    map.put("stockType","制造中心采购公开求购");
                    map.put("status",((Stock) o).getIdMapping().getStatus());
                    map.put("endDate",((Stock) o).getEndDate()==null?"":simpleDateFormat.format(((Stock) o).getEndDate()));
                    map.put("enquire","");
                }else if(status.equals("C001-51")){
                    //未通过采购审批采购计划
                    map.put("id",((Stock) o).getId().toString());
                    map.put("stockType","公开求购");
                    map.put("progress",((Stock) o).getIdMapping().getStatus());
                    map.put("author",((Stock) o).getAuthor());
                }
            }
            //把map存入list中
            objects.add(map);
        }
        //重新给EasyUiDataGrid rows属性赋值
        stockPageAll.setRows(objects);
        return stockPageAll;
    }

    /**
     * 查询采购计划详情
     * @param stockNum  采购计划编号
     * @param model
     * @param stockType  采购类型 详情 == 1  驳回 == 2
     * @return
     */
    @RequestMapping("stockDetails")
    public String stockDetails(String stockNum, Model model,String stockType){
        //返回路径
        String url = "";
        //供应商编号
        Map<Long,Long> supplierIdList = new HashMap<Long,Long>();

        //调用查询 采购计划 + 物资 信息
        Stock stockAndIdMapperAndOrders = stockService.findStockAndIdMapperAndOrders(stockNum);
        model.addAttribute("stockIdMapperOrders",stockAndIdMapperAndOrders);
        //stockType == 1 返回采购计划详情页
        if(stockType.equals("1")){
            url = "xjfatz_xjfamx";
        }else if(stockType.equals("2")){
            //通过需求计划物资编号查询物资序号
            System.out.println(stockAndIdMapperAndOrders.getIdMapping().getOrders().getMaterialCode());
            Material byMaterialNum = materialService.findByMaterialNum(stockAndIdMapperAndOrders.getIdMapping().getOrders().getMaterialCode());
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
            model.addAttribute("suppliers",suppliers);
            //返回 驳回详情页
            url = "xjfatz_xjfamx3";
        }
        return url;
    }

    /**
     * 采购计划报批 + 采购计划下达
     * @param status 对照编号 状态
     * @param stockNum 采购计划编号
     * @return
     */
    @RequestMapping("stockUpdateIdMapperStatus")
    @ResponseBody
    public String stockUpdateIdMapperStatus(String status,String stockNum){
        //修改编号对照状态
        return stockService.updateStockByStockNumIdmaStatus(status,stockNum).toString();
    }

    @RequestMapping("stockUpdateRejectedApproval")
    public String stockUpdateRejectedApproval(Stock stock,Orders orders,Integer stoId){
        stock.setId((long)stoId);
        //修改采购计划项
        Integer stockRes = stockService.updateStockById(stock);
        if(stockRes>0){
           //通过采购计划编号查询需求计划项  采购计划+编号对照+需求计划
            Stock stockAndIdMapperAndOrders = stockService.findStockAndIdMapperAndOrders(stock.getStockNum());
            orders.setId(stockAndIdMapperAndOrders.getIdMapping().getOrders().getId());
            Integer ordersRes = ordersService.updateOrderById(orders);
             if(ordersRes>0){
                 //修改采购计划状态
                 stockService.updateStockByStockNumIdmaStatus("C001-40",stock.getStockNum());
                 return "Project_list";
             }
        }
        return "";
    }
}
