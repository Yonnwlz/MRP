package com.tuling.service.impl;

import com.tuling.dao.SysMenuRoleMapper;
import com.tuling.dao.SysMenusMapper;
import com.tuling.dao.SysRolesMapper;
import com.tuling.dao.SysUserRoleMapper;
import com.tuling.entity.*;
import com.tuling.service.SysMenusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenusService_impl implements SysMenusService {
    //注入
    @Resource
    private SysMenusMapper sysMenusMapper;

    //注入
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Resource
    private SysRolesMapper sysRolesMapper;

    @Resource
    private SysMenuRoleMapper sysMenuRoleMapper;
    @Override
    public List<SysMenus> findfatAll(Integer id) {
        //存入跟节点
        List<SysMenus> list = new ArrayList<>();

        //查询用户角色表
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andUserIdEqualTo((long)id);
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectByExample(example);
        if(sysUserRoles.size()>0){
            SysUserRole sysUserRole = sysUserRoles.get(0);
            //查询菜单角色
            SysMenuRoleExample example1 = new SysMenuRoleExample();
            example1.createCriteria().andRoleIdEqualTo(sysUserRole.getRoleId());
            List<SysMenuRole> sysMenuRoles = sysMenuRoleMapper.selectByExample(example1);
            List<SysMenus> sysMenus = sysMenusMapper.selectByIdMenusqier(sysMenuRoles);


            for (SysMenus menus:sysMenus) {
                if(menus.getParentId()==1){
                    list.add(menus);
                }
            }
        }
        return findSysMenusSouAndNode(list);
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
