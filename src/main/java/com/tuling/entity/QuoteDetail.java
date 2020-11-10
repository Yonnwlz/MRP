package com.tuling.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class QuoteDetail implements Serializable {
    private Long id;

    private Long quoteId;

    private Long orderId;

    private String materialCode;    //物资编号

    private String materialName;    //物资名称

    private String measureUnit;     //物资数量

    private String standard;

    private String factory;

    private String prodYear;

    private String wrap;

    private String amount;      //数量

    private BigDecimal unitPrice;       //单价

    private BigDecimal sumPrice;

    private BigDecimal mixPrice;        //运杂费

    private BigDecimal otherPrice;      //其他费用

    private BigDecimal totalPrice;   //总计

    private Date startDate;

    private Date endDate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Long quoteId) {
        this.quoteId = quoteId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode == null ? null : materialCode.trim();
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName == null ? null : materialName.trim();
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit == null ? null : measureUnit.trim();
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard == null ? null : standard.trim();
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory == null ? null : factory.trim();
    }

    public String getProdYear() {
        return prodYear;
    }

    public void setProdYear(String prodYear) {
        this.prodYear = prodYear == null ? null : prodYear.trim();
    }

    public String getWrap() {
        return wrap;
    }

    public void setWrap(String wrap) {
        this.wrap = wrap == null ? null : wrap.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    public BigDecimal getMixPrice() {
        return mixPrice;
    }

    public void setMixPrice(BigDecimal mixPrice) {
        this.mixPrice = mixPrice;
    }

    public BigDecimal getOtherPrice() {
        return otherPrice;
    }

    public void setOtherPrice(BigDecimal otherPrice) {
        this.otherPrice = otherPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "QuoteDetail{" +
                "id=" + id +
                ", quoteId=" + quoteId +
                ", orderId=" + orderId +
                ", materialCode='" + materialCode + '\'' +
                ", materialName='" + materialName + '\'' +
                ", measureUnit='" + measureUnit + '\'' +
                ", standard='" + standard + '\'' +
                ", factory='" + factory + '\'' +
                ", prodYear='" + prodYear + '\'' +
                ", wrap='" + wrap + '\'' +
                ", amount='" + amount + '\'' +
                ", unitPrice=" + unitPrice +
                ", sumPrice=" + sumPrice +
                ", mixPrice=" + mixPrice +
                ", otherPrice=" + otherPrice +
                ", totalPrice=" + totalPrice +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}