package com.tuling.service;

import com.tuling.entity.EasyUiDataGrid;
import com.tuling.entity.Material;

import java.util.List;

/**
 * 物资接口
 */
public interface MaterialService {

    /**
     * 分页查询
     * @return
     */
    public EasyUiDataGrid findMaterialCountPageHelper(Integer curPage, Integer pageSize);

    /**
     * 通过id查询 物资信息
     * @param id
     * @return
     */
    public Material findByIdMaterial(Integer id);

    /**
     * 通过物资编号 查询物资信息
     * @param matNum    物资编号
     * @return
     */
    public Material findByMaterialNum(String matNum);
}
