package com.tuling.service.impl;

import com.tuling.dao.MaterialMapper;
import com.tuling.dao.SysMenusMapper;
import com.tuling.entity.EasyUiDataGrid;
import com.tuling.entity.Material;
import com.tuling.service.MaterialService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 物资信息实现接口
 */
@Service
public class MaterialService_impl implements MaterialService {
    //注入 MaterialMapper 物资信息
    @Resource
    private MaterialMapper materialMapper;

    /**
     * 分页
     * @param curPage
     * @param pageSize
     * @return
     */
    @Override
    public EasyUiDataGrid findMaterialCountPageHelper(Integer curPage, Integer pageSize) {
        //创建分页对象
        EasyUiDataGrid easyUiDataGrid = new EasyUiDataGrid();
        easyUiDataGrid.setTotal(materialMapper.selectMaterialCount());  //查询总条数
        easyUiDataGrid.setRows(materialMapper.selectMaterialAll((curPage-1)*pageSize,pageSize));
        return easyUiDataGrid;
    }

    @Override
    public Material findByIdMaterial(Integer id) {
        //通过id查询物资信息
        return materialMapper.selectByPrimaryKey((long)id);
    }
}
