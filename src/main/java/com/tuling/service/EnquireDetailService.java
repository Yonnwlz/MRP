package com.tuling.service;

import com.tuling.entity.EnquireDetail;

/**
 * 询价书明细接口
 */

public interface EnquireDetailService {

    /**
     * 添加询价书明细
     * @param enquireDetail
     * @return
     */
    public Integer insertEnquireDetail(EnquireDetail enquireDetail);

    /**
     * 通过询价书序号 删除询价书详情
     * @param enquireId     询价书序号
     * @return
     */
    public Integer deleteEnquireDetailByEnquireId(Integer enquireId);
}
