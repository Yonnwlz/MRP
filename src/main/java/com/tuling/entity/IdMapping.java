package com.tuling.entity;

import java.io.Serializable;

/**
 * 编号对照实体类
 */
public class IdMapping implements Serializable {
    private Long id;            //序号

    private Long orderId;       //需求计划序号

    private Long stockId;       //采购计划序号

    private Long enquireId;     //询价书序号

    private Long contAppId;     //合同申请序号

    private Long quoteId;       //报价书序号

    private Long contId;        //合同序号


    /**
     * 采购状态代码
     * C001-10：需求计划未确认
     * C001-20：未编采购计划
     * C001-30：采购计划未报批
     * C001-40：采购计划未审批
     * C001-50：采购计划未下达
     * C001-51：采购计划审批不通过
     * C001-60：未编询价书
     * C001-70：询价书未发出
     * C001-80：询价书已发出
     * C001-90：询价已截止
     * C001-100：已揭示报价
     * C001-110：合同申请未报批
     * C001-120：合同申请未审核
     * C001-130：合同申请计划员已审核
     */
    private String status;

    private Orders orders;

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getEnquireId() {
        return enquireId;
    }

    public void setEnquireId(Long enquireId) {
        this.enquireId = enquireId;
    }

    public Long getContAppId() {
        return contAppId;
    }

    public void setContAppId(Long contAppId) {
        this.contAppId = contAppId;
    }

    public Long getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Long quoteId) {
        this.quoteId = quoteId;
    }

    public Long getContId() {
        return contId;
    }

    public void setContId(Long contId) {
        this.contId = contId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}