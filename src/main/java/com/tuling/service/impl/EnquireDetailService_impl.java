package com.tuling.service.impl;


import com.tuling.dao.EnquireDetailMapper;
import com.tuling.entity.EnquireDetail;
import com.tuling.entity.EnquireDetailExample;
import com.tuling.service.EnquireDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 询价书明细接口实现类
 */

@Service
public class EnquireDetailService_impl implements EnquireDetailService {
    //注入询价书明显mapper EnquireDetailMapper
    @Resource
    private EnquireDetailMapper enquireDetailMapper;

    @Override
    public Integer insertEnquireDetail(EnquireDetail enquireDetail) {
        //添加询价书明细
        return enquireDetailMapper.insertSelective(enquireDetail);
    }

    @Override
    public Integer deleteEnquireDetailByEnquireId(Integer enquireId) {
        //通过询价书序号 删除询价书详情
        EnquireDetailExample example = new EnquireDetailExample();
        example.createCriteria().andEnquireIdEqualTo((long)enquireId);
        return enquireDetailMapper.deleteByExample(example);
    }
}
