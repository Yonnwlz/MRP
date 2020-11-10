package com.tuling.dao;

import com.sun.org.apache.xpath.internal.operations.Quo;
import com.tuling.entity.Quote;
import com.tuling.entity.QuoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuoteMapper {
    long countByExample(QuoteExample example);

    int deleteByExample(QuoteExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Quote record);

    int insertSelective(Quote record);

    List<Quote> selectByExample(QuoteExample example);

    Quote selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Quote record, @Param("example") QuoteExample example);

    int updateByExample(@Param("record") Quote record, @Param("example") QuoteExample example);

    int updateByPrimaryKeySelective(Quote record);

    int updateByPrimaryKey(Quote record);

    /**
     * 查询最大流水号
     * @param quoteNum
     * @return
     */
    Quote selectByCount(String quoteNum);

    /**
     * 报价书+报价详情
     * @param quote
     * @return
     */
    Quote selectQuoteAndQuoteDetail(Quote quote);
}