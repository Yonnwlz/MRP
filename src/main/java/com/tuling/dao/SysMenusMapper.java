package com.tuling.dao;

import com.tuling.entity.SysMenuRole;
import com.tuling.entity.SysMenus;
import com.tuling.entity.SysMenusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMenusMapper {
    long countByExample(SysMenusExample example);

    int deleteByExample(SysMenusExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysMenus record);

    int insertSelective(SysMenus record);

    List<SysMenus> selectByExample(SysMenusExample example);

    SysMenus selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysMenus record, @Param("example") SysMenusExample example);

    int updateByExample(@Param("record") SysMenus record, @Param("example") SysMenusExample example);

    int updateByPrimaryKeySelective(SysMenus record);

    int updateByPrimaryKey(SysMenus record);

    List<SysMenus> selectByIdMenusqier(List<SysMenuRole> list);
}