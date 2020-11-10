package com.tuling.service;

import com.tuling.entity.EasyUiDataGrid;
import com.tuling.entity.Enquire;

import java.util.List;

/**
 * 询价书接口
 */

public interface EnquireService {

    /**
     * 查询当前最大流水号
     * @param enquireNum
     * @return
     */
    public Enquire findByEnquireNumMax(String enquireNum);

    /**
     * 添加询价书
     * @param enquire
     * @return
     */
    public Integer insertEnquire(Enquire enquire);

    /**
     * 查询所有询价书 + 分页 + 模糊查询
     * @param curPage
     * @param pageSize
     * @return
     */
    public EasyUiDataGrid findEnquireStatusNameAllPaging(Integer curPage, Integer pageSize, String enquireName,String issued);


    /**
     * 通过询价书序号 查询询价书信息
     * @param enquireId  询价书序号
     * @return
     */
    public Enquire findEnquireByIdAndIdMapper(Integer enquireId);

    /**
     * 修改询价书
     * @param enquire
     * @return
     */
    public Integer updateByIdEnquire(Enquire enquire);

    /**
     * 删除询价书
     * @param enquireId  询价书序号
     * @return
     */
    public Integer deleteByIdEnquire(Integer enquireId);

    /**
     * 查询询价书详情
     * @param enquireId  询价书序号
     * @return
     */
    public Enquire findEnquireByIdDetail(Integer enquireId);

    /**
     * 查询询价书
     * @param enquireId 询价书序号
     * @return
     */
    public Enquire findEnquireById(Integer enquireId);
}
