package com.tuling.service;

import com.tuling.entity.QuoteDetail;

/**
 * 报价书详情接口
 */
public interface QuoteDetailService {

    /**
     * 添加报价书详情
     * @param quoteDetail
     * @return
     */
    public Integer insertQuoteDetail(QuoteDetail quoteDetail);
}
