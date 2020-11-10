package com.tuling.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OfferDetails {
    private Long enquireId;

    private Long supplierId;

    private String queTitle;    //报价书标题

    private Date queDate;       //报价时间

    private Date endDate;       //询价截止时间

    private BigDecimal sumAmount;   //数量合计

    private BigDecimal overallPrice;    //总金额(元)

    private String quoRemark;       //报价说明
}
