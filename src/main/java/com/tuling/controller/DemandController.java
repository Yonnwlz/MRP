package com.tuling.controller;

import com.tuling.entity.*;
import com.tuling.service.IdMappingService;
import com.tuling.service.MaterialService;
import com.tuling.service.OrdersService;
import com.tuling.service.SysMenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 需求计划控制层
 */
@Controller
public class DemandController {
    //注入 物资业务层 materialService
    @Autowired
    private MaterialService materialService;

    //注入 菜单信息业务层 SysMenusService
    @Autowired
    private SysMenusService sysMenusService;

    //注入 需求计划业务层    OrdersService
    @Autowired
    private OrdersService ordersService;

    //注入 编号对照业务层    IdMappingService
    @Autowired
    private IdMappingService idMappingService;

    /**
     * 菜单栏
     * @return
     */
    @RequestMapping("SysMenuSouAndNode")
    @ResponseBody
    public List<SysMenus> SysMenuSouAndNode(Integer userid){
        List<SysMenus> sysMenus = sysMenusService.findfatAll(userid);
        return sysMenus;
    }

    /**
     * 分页查询
     * @param curPage   当前页数
     * @param pageSize  当前显示条数
     * @return
     */
    @RequestMapping("selMatPageHelper")
    @ResponseBody
    public EasyUiDataGrid selMatPageHelper(@RequestParam(defaultValue = "1") Integer curPage, @RequestParam(defaultValue = "4") Integer pageSize){
        return materialService.findMaterialCountPageHelper(curPage,pageSize);
    }

    /**
     * 物资信息传递
     * @param orderId
     * @return
     */
    @RequestMapping("passMaterial")
    public ModelAndView addOrder(String orderId){
        ModelAndView mv = new ModelAndView("Order_newform");
        //将当前选择的物资序号 进行存储
        ArrayList<Material> matList = new ArrayList<Material>();
        //将前端数据(物资序号)进行切割
        String[] split = orderId.split(",");
        //遍历 split
        for (String a: split) {
            //通过物资序号 查询信息项
            Material byIdMaterial = materialService.findByIdMaterial(Integer.parseInt(a));
            matList.add(byIdMaterial);
        }
        mv.addObject("matList",matList);
        return mv;
    }

    /**
     * 保存需求计划
     * @param orders
     * @return
     */
    @RequestMapping("saveSessionMaterial")
    @ResponseBody
    public String saveSessionMaterial(Orders orders, HttpServletRequest request){
        ArrayList<Orders> ordList  = new ArrayList<Orders>();
        //流失号
        long orderNum =0;
        //当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        //request.getSession().getAttribute("ordList")!=null  把之前的session取出来 赋值给list
        if(request.getSession().getAttribute("ordList")!=null){
            ordList = (ArrayList<Orders>) request.getSession().getAttribute("ordList");
            //获取当前最大流失号
            String orderNum1 = ordList.get(ordList.size() - 1).getOrderNum();
            //截取最后七位数
            orderNum = Long.valueOf(orderNum1)+1;

        }else {
            //调用查询流水号的方法
            Orders byOrderNum = ordersService.findByOrderNum("100");
            //截取最后七位数
            orderNum = Long.valueOf(byOrderNum.getOrderNum())+1;
        }
        //需求计划编号  100+当前日期+5位流水号
        String ordernum = "100"+simpleDateFormat.format(date)+String.valueOf(orderNum).substring(11);
        //赋值需求计划编号
        orders.setOrderNum(ordernum);
        //需求对象存入集合中
        ordList.add(orders);
        request.getSession().setAttribute("ordList",ordList);
        return "true";
    }
    /**
     * 添加需求计划项
     * @param
     * @return
     */
    @RequestMapping("addOrders")
    public String addOrders(HttpServletRequest request){
        //获取所有选择的物资 需求计划项
        ArrayList<Orders> list = (ArrayList<Orders>) request.getSession().getAttribute("ordList");
        Integer integer1 = 0;
        //遍历所有需求计划项
        for (Orders s:list) {
            //添加需求计划项
            Integer integer = ordersService.insertOrders(s);
            if(integer>0){
                //添加编号对照项
                IdMapping idMapping = new IdMapping();
                idMapping.setOrderId(s.getId());
                idMapping.setStatus("C001-10");
                integer1 = idMappingService.insertIdMapping(idMapping);
            }
        }
        //统计完 清空session
        request.getSession().removeAttribute("ordList");
        return "Order_ytb_list";
    }

    /**
     * 查询需求计划信息
     * @param curPage  当前页数
     * @param pageSize  当前页数条数
     * @return
     */
    @RequestMapping("orderAllPaging")
    @ResponseBody
    public EasyUiDataGrid orderAllPaging(@RequestParam(defaultValue = "1") Integer curPage,@RequestParam(defaultValue = "10") Integer pageSize){
        //查询出来 总数量 + 需求计划信息项
        EasyUiDataGrid orderOneIdMapper = ordersService.findOrderOneIdMapper(curPage, pageSize,"e",null);

        //把所有需求信息项取出来  通过map 进行存储
        List<?> rows = orderOneIdMapper.getRows();
        ArrayList arrayList = new ArrayList();
        for (Object s: rows) {
            Orders or = (Orders) s;
            Map map = new HashMap();
            map.put("id",or.getId());
            map.put("idMapper",or.getIdMapping().getId());
            map.put("materialCode",or.getMaterialCode());
            map.put("materialName",or.getMaterialName());
            map.put("amount",or.getAmount());
            map.put("type","制造中心采购公开求购");
            map.put("status",or.getIdMapping().getStatus());
            //把map  放进list中
            arrayList.add(map);
        }
        //给EasyUiDataGrid   Rows 重新赋值
        orderOneIdMapper.setRows(arrayList);
        return orderOneIdMapper;
    }

    /**
     * 需求计划保存
     * @param idMapperId
     * @return
     */
    @RequestMapping("updateOrderIdState")
    @ResponseBody
    public String updateOrderIdState(@RequestParam("idMapperId") Integer idMapperId){
            IdMapping idMapping = new IdMapping();
            System.out.println(idMapperId+"保存id");
            idMapping.setId((long)idMapperId);
            idMapping.setStatus("C001-20");
            //通过id  修改对照信息
            Integer integer = idMappingService.updateById(idMapping);
            return integer+"";
    }

    /**
     * 查询需求计划信息项
     * @param ordersid 通过需求计划id查询
     * @return
     */
    @RequestMapping("selOrdersByIdView")
    public ModelAndView selOrdersByIdView(Integer ordersid){
        ModelAndView mv = new ModelAndView("print_order_detail");
        //查询需求计划项
        Orders byIdOrders = ordersService.findByIdOrders(ordersid);
        mv.addObject("byIdOrders",byIdOrders);
        return mv;
    }
}
