package com.tuling.service.impl;

import com.tuling.dao.SysMenusMapper;
import com.tuling.entity.SysMenus;
import com.tuling.entity.SysMenusAttributes;
import com.tuling.entity.SysMenusExample;
import com.tuling.service.SysMenusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysMenusService_impl implements SysMenusService {
    //注入
    @Resource
    private SysMenusMapper sysMenusMapper;

    /**
     * 查询所有父节点
     * @param id    通过id查询
     * @return
     */
    @Override
    public List<SysMenus> findfatAll(Integer id) {
        SysMenusExample sysMenusExample = new SysMenusExample();
        sysMenusExample.createCriteria().andIdEqualTo((long)id);
        List<SysMenus> sysMenus = sysMenusMapper.selectByExample(sysMenusExample);
        return findSysMenusSouAndNode(sysMenus);
    }
    /**
     * 查询所有子节点
     */
    public List<SysMenus> findSysMenusSouAndNode(List<SysMenus> list){
        //遍历list
        for (SysMenus s:list) {
            //根据父节点编号查询所有子节点
            SysMenusExample sysMenusExample = new SysMenusExample();
            sysMenusExample.createCriteria().andParentIdEqualTo((long)s.getId());
            List<SysMenus> sysMenus = sysMenusMapper.selectByExample(sysMenusExample);
            //把查询出的子节点放入父节点中
            s.setChildren(sysMenus);
            //使用SysMenusAttributes对象 存储 json自定义属性 attributes
            SysMenusAttributes menus = new SysMenusAttributes();
            menus.setTip(s.getTip());
            menus.setDescn(s.getDescn());
            menus.setImageUrl(s.getImageUrl());
            menus.setLinkUrl(s.getLinkUrl());
            menus.setTarget(s.getTarget());
            menus.setStatus(s.getStatus());

            s.setAttributes(menus);
            //递归查询
            findSysMenusSouAndNode(sysMenus);
        }

        return list;
    }
}
