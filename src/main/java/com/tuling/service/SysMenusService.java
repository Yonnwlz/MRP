package com.tuling.service;

import com.tuling.entity.SysMenus;

import java.util.List;

public interface SysMenusService {

    /**
     * 查询所有父节点
     * @param id    通过id查询
     * @return
     */
    public List<SysMenus> findfatAll(Integer id);
}
