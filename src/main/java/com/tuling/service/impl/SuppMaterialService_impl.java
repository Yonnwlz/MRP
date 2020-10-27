package com.tuling.service.impl;

import com.tuling.dao.SuppMaterialMapper;
import com.tuling.entity.SuppMaterial;
import com.tuling.entity.SuppMaterialExample;
import com.tuling.service.SuppMaterialService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 供应商对应商品接口实现类
 */
@Service
public class SuppMaterialService_impl implements SuppMaterialService {
    //注入 供应商对应商品 mapper
    @Resource
    private SuppMaterialMapper suppMaterialMapper;

    @Override
    public List<SuppMaterial> findBySuppMaterial(Integer materialId) {
        //通过商品序号 查询相对应的供应商
        SuppMaterialExample example = new SuppMaterialExample();
        example.createCriteria().andMaterialIdEqualTo((long)materialId);
        List<SuppMaterial> suppMaterials = suppMaterialMapper.selectByExample(example);
        //判断是否查到数据
        if(suppMaterials.size()>0){
            return suppMaterials;
        }else {
            return null;
        }
    }
}
