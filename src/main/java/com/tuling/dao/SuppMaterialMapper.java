package com.tuling.dao;

import com.tuling.entity.SuppMaterial;
import com.tuling.entity.SuppMaterialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SuppMaterialMapper {
    long countByExample(SuppMaterialExample example);

    int deleteByExample(SuppMaterialExample example);

    int insert(SuppMaterial record);

    int insertSelective(SuppMaterial record);

    List<SuppMaterial> selectByExample(SuppMaterialExample example);

    int updateByExampleSelective(@Param("record") SuppMaterial record, @Param("example") SuppMaterialExample example);

    int updateByExample(@Param("record") SuppMaterial record, @Param("example") SuppMaterialExample example);
}