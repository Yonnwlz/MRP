package com.tuling.entity;

import java.io.Serializable;
import java.util.Date;

public class EnquireDetail implements Serializable {
    private Long id;

    private Long enquireId;     //询价书序号

    private Long orderId;       //需求计划序号

    private String materialCode;    //物资编码

    private String materialName;    //物资名称

    private String amount;          //数量

    private String measureUnit;     //单位

    private Date startDate;         //开始到货期

    private Date endDate;           //结束到货期

    private String standard;

    private String factory;

    private String prodYear;

    private String wrap;

    private String remark;          //备注

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnquireId() {
        return enquireId;
    }

    public void setEnquireId(Long enquireId) {
        this.enquireId = enquireId;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit == null ? null : measureUnit.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return "EnquireDetail{" +
                "id=" + id +
                ", enquireId=" + enquireId +
                ", orderId=" + orderId +
                ", materialCode='" + materialCode + '\'' +
                ", materialName='" + materialName + '\'' +
                ", amount='" + amount + '\'' +
                ", measureUnit='" + measureUnit + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", standard='" + standard + '\'' +
                ", factory='" + factory + '\'' +
                ", prodYear='" + prodYear + '\'' +
                ", wrap='" + wrap + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}