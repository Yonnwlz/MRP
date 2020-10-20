package com.tuling.controller;

import com.tuling.entity.SysMenus;
import com.tuling.service.SysMenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SysMenusController {
    //注入 菜单信息 SysMenusService
    @Autowired
    private SysMenusService sysMenusService;


    @RequestMapping("SysMenuSouAndNode")
    @ResponseBody
    public List<SysMenus> text(){
        List<SysMenus> sysMenus = sysMenusService.findfatAll(1);
        return sysMenus;
    }
}
