package com.tuling.service.impl;

import com.tuling.dao.QuoteMapper;
import com.tuling.entity.Quote;
import com.tuling.service.QuoteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 报价接口实现类
 */
@Service
public class QuoteService_impl implements QuoteService {
    //注入 报价mapper
    @Resource
    private QuoteMapper quoteMapper;

    @Override
    public Integer insertQuote(Quote quote) {
        //添加报价信息
        return quoteMapper.insertSelective(quote);
    }

    @Override
    public Quote findByQuoteNumMax(String quoteNum) {
        //查询最大流水号
        return quoteMapper.selectByCount(quoteNum);
    }

    @Override
    public Quote findQueryObjetQuoteAndQuoteDetail(Quote quote) {
        //报价书+报价详情 查询 quote条件
        return quoteMapper.selectQuoteAndQuoteDetail(quote);
    }
}
