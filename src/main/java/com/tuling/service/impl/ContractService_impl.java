package com.tuling.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tuling.dao.ContractMapper;
import com.tuling.entity.Contract;
import com.tuling.entity.EasyUiDataGrid;
import com.tuling.service.ContractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 合同接口实现类
 */
@Service
public class ContractService_impl implements ContractService {
    //注入合同 mapper
    @Resource
    private ContractMapper contractMapper;

    @Override
    public Integer insertContract(Contract contract) {
        return contractMapper.insertSelective(contract);
    }

    @Override
    public Integer findContractSumCount() {
        return contractMapper.selectSumCount();
    }

    @Override
    public Contract findContractCountMax(String contNum) {
        return contractMapper.selectContractCountMax(contNum);
    }

    @Override
    public EasyUiDataGrid findContractAndIdMapper(Integer curPage, Integer pageSize, String status) {
        //分页
        Page<Object> page = PageHelper.startPage(curPage, pageSize);
        EasyUiDataGrid easyUiDataGrid = new EasyUiDataGrid();
        List<Contract> contracts = contractMapper.selectContractAndIdMapper(status);
        easyUiDataGrid.setRows(contracts);
        easyUiDataGrid.setTotal((int)page.getTotal());
        return easyUiDataGrid;
    }

    @Override
    public Contract findContractById(Integer contId) {
        return contractMapper.selectByPrimaryKey((long)contId);
    }
}
