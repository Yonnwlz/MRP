package com.tuling.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tuling.dao.EnquireMapper;
import com.tuling.entity.EasyUiDataGrid;
import com.tuling.entity.Enquire;
import com.tuling.service.EnquireService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 询价书接口实现类
 */

@Service
public class EnquireService_impl implements EnquireService {
    //注入 询价书mapper EnquireMapper
    @Resource
    private EnquireMapper enquireMapper;

    @Override
    public Enquire findByEnquireNumMax(String enquireNum) {
        return enquireMapper.selectByCount(enquireNum);
    }

    @Override
    public Integer insertEnquire(Enquire enquire) {
        //添加询价书
        return enquireMapper.insertSelective(enquire);
    }

    @Override
    public EasyUiDataGrid findEnquireStatusNameAllPaging(Integer curPage, Integer pageSize, String enquireName,String issued) {
        //使用分页插件
        Page<Object> page = PageHelper.startPage(curPage,pageSize);
        //查询所有询价书
        List<Enquire> enquires = enquireMapper.selectEnquireAndIdMapper(enquireName,issued);
        EasyUiDataGrid easyUiDataGrid = new EasyUiDataGrid();
        easyUiDataGrid.setTotal((int)page.getTotal());
        easyUiDataGrid.setRows(enquires);
        return easyUiDataGrid;
    }

    @Override
    public Enquire findEnquireByIdAndIdMapper(Integer enquireId) {
        return enquireMapper.selectEnquireByIdAndIdMapper(enquireId);
    }

    @Override
    public Integer updateByIdEnquire(Enquire enquire) {
        //修改询价书
        return enquireMapper.updateByPrimaryKeySelective(enquire);
    }

    @Override
    public Integer deleteByIdEnquire(Integer enquireId) {
        //删除询价书
        return enquireMapper.deleteByPrimaryKey((long)enquireId);
    }

    @Override
    public Enquire findEnquireByIdDetail(Integer enquireId) {
        //查询询价书详情
        return enquireMapper.selectEnquireByIdAndEnquireDetail(enquireId);
    }

    @Override
    public Enquire findEnquireById(Integer enquireId) {
        //查询询价书
        return enquireMapper.selectByPrimaryKey((long)enquireId);
    }
}
