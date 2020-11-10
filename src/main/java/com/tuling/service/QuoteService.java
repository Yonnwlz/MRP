package com.tuling.service;

import com.tuling.entity.Quote;

/**
 * 报价接口
 */

public interface QuoteService {
    /**
     * 添加报价信息
     * @param quote
     * @return
     */
    public Integer insertQuote(Quote quote);

    /**
     * 查询最大流水号
     * @param quoteNum
     * @return
     */
    public Quote findByQuoteNumMax(String quoteNum);

    /**
     * 报价书+报价详情查询
     * @param quote  条件
     * @return
     */
    public Quote findQueryObjetQuoteAndQuoteDetail(Quote quote);
}
