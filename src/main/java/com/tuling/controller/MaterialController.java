package com.tuling.controller;

import com.tuling.entity.EasyUiDataGrid;
import com.tuling.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;

/**
 * 物资信息控制层
 */
@Controller
public class MaterialController {
    //注入 物资业务层 materialService
    @Autowired
    private MaterialService materialService;

    /**
     * 分页查询
     * @param curPage   当前页数
     * @param pageSize  当前显示条数
     * @return
     */
    @RequestMapping("selMatPageHelper")
    @ResponseBody
    public EasyUiDataGrid selMatPageHelper(@RequestParam(defaultValue = "1") Integer curPage, @RequestParam(defaultValue = "2") Integer pageSize){
        return materialService.findMaterialCountPageHelper(curPage,pageSize);
    }
}
