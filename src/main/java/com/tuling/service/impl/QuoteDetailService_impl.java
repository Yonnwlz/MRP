package com.tuling.service.impl;

import com.tuling.dao.QuoteDetailMapper;
import com.tuling.entity.QuoteDetail;
import com.tuling.service.QuoteDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 报价书详情接口实现类
 */
@Service
public class QuoteDetailService_impl implements QuoteDetailService {
    //注入报价书详情mapper
    @Autowired
    private QuoteDetailMapper quoteDetailMapper;

    @Override
    public Integer insertQuoteDetail(QuoteDetail quoteDetail) {
        //添加报价书详情项
        return quoteDetailMapper.insertSelective(quoteDetail);
    }
}
