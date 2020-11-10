package com.tuling.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tuling.dao.ContractApplyMapper;
import com.tuling.entity.ContractApply;
import com.tuling.entity.EasyUiDataGrid;
import com.tuling.service.ContractApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 申请合同接口实现类
 */

@Service
public class ContractApplyService_impl implements ContractApplyService {
    //注入 申请合同mapper
    @Resource
    private ContractApplyMapper contractApplyMapper;

    @Override
    public Integer insertContractApply(ContractApply contractApply) {
        //添加申请合同
        return contractApplyMapper.insertSelective(contractApply);
    }

    @Override
    public ContractApply findContractApplyCount(String contAppNum) {
        //查询最大流水号
        return contractApplyMapper.selectContractApplyCount(contAppNum);
    }

    @Override
    public Integer updateContractApply(ContractApply contractApply) {
        //修改合同信息
        return contractApplyMapper.updateByPrimaryKeySelective(contractApply);
    }

    @Override
    public ContractApply findByIdContractApply(Integer contId) {
        //通过合同申请序号查询合同申请项
        return contractApplyMapper.selectByPrimaryKey((long)contId);
    }

    @Override
    public EasyUiDataGrid findContractApplyAndIdMapperAll(Integer curPage,Integer pageSize,String status) {
        //使用分页插件
        Page<Object> page = PageHelper.startPage(curPage,pageSize);
        EasyUiDataGrid easyUiDataGrid = new EasyUiDataGrid();
        List<ContractApply> contractApplies = contractApplyMapper.selectContractApplyAndIdMapper(status);
        easyUiDataGrid.setRows(contractApplies);
        easyUiDataGrid.setTotal((int)page.getTotal());
        return easyUiDataGrid;
    }
}
