package com.tuling.controller;

import com.tuling.entity.EasyUiDataGrid;
import com.tuling.entity.Stock;
import com.tuling.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
            map.put("progress","待审");
            map.put("budget",((Stock) o).getBudget()+"");
            map.put("startDate",((Stock) o).getStartDate()==null?"":simpleDateFormat.format(((Stock) o).getStartDate()));
            map.put("endDate",((Stock) o).getEndDate()==null?"":simpleDateFormat.format(((Stock) o).getEndDate()));
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
    @ResponseBody
    public String stockExamination(String stockNum,String status){
        //调用通过采购计划编号修改对照编号状态
        Integer integer = stockService.updateStockByStockNumIdmaStatus(null,status,stockNum);
        if(integer>0){
            System.out.println("修改成功！");
        }
        return integer+"";
    }
}
