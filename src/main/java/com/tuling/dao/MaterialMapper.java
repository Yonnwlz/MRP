package com.tuling.dao;

import com.tuling.entity.Material;
import com.tuling.entity.MaterialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface MaterialMapper {
    long countByExample(MaterialExample example);

    int deleteByExample(MaterialExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Material record);

    int insertSelective(Material record);

    List<Material> selectByExample(MaterialExample example);

    Material selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Material record, @Param("example") MaterialExample example);

    int updateByExample(@Param("record") Material record, @Param("example") MaterialExample example);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);

    /**
     * 查询总条数
     * @return
     */
    @Select("select count(*) from MATERIAL")
    Integer selectMaterialCount();

    /**
     * 分页
     * @param curPage 当前页数
     * @param pageSize 当前有几条
     * @return
     */
    public List<Material> selectMaterialAll(Integer curPage, Integer pageSize);
}