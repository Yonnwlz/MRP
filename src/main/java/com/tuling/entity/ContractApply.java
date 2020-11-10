package com.tuling.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ContractApply implements Serializable {
    private Long id;

    private String contAppNum;      //合同申请编号

    private String statQuote;       //报价份数

    private String topQuote;        //最高报价

    private String lowQuote;        //最低报价

    private BigDecimal allSumPrice;     //总金额

    private String remark;          //合同申请备注

    private String authorId;        //编制人序号

    private String author;          //编制人

    private String planerId;

    private String planer;

    private String planAgree;

    private String planOpinion;

    private Date planDate;

    private String leaderId;        //财务部长序号

    private String leader;          //部长

    private String leadAgree;       //部长是否同意    S002-0不同意  S002-1同意

    private String leadOpinion;     //部长意见

    private Date leadDate;          //部长审核时间

    private IdMapping idMapping;

    public IdMapping getIdMapping() {
        return idMapping;
    }

    public void setIdMapping(IdMapping idMapping) {
        this.idMapping = idMapping;
    }

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContAppNum() {
        return contAppNum;
    }

    public void setContAppNum(String contAppNum) {
        this.contAppNum = contAppNum == null ? null : contAppNum.trim();
    }

    public String getStatQuote() {
        return statQuote;
    }

    public void setStatQuote(String statQuote) {
        this.statQuote = statQuote == null ? null : statQuote.trim();
    }

    public String getTopQuote() {
        return topQuote;
    }

    public void setTopQuote(String topQuote) {
        this.topQuote = topQuote == null ? null : topQuote.trim();
    }

    public String getLowQuote() {
        return lowQuote;
    }

    public void setLowQuote(String lowQuote) {
        this.lowQuote = lowQuote == null ? null : lowQuote.trim();
    }

    public BigDecimal getAllSumPrice() {
        return allSumPrice;
    }

    public void setAllSumPrice(BigDecimal allSumPrice) {
        this.allSumPrice = allSumPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId == null ? null : authorId.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getPlanerId() {
        return planerId;
    }

    public void setPlanerId(String planerId) {
        this.planerId = planerId == null ? null : planerId.trim();
    }

    public String getPlaner() {
        return planer;
    }

    public void setPlaner(String planer) {
        this.planer = planer == null ? null : planer.trim();
    }

    public String getPlanAgree() {
        return planAgree;
    }

    public void setPlanAgree(String planAgree) {
        this.planAgree = planAgree == null ? null : planAgree.trim();
    }

    public String getPlanOpinion() {
        return planOpinion;
    }

    public void setPlanOpinion(String planOpinion) {
        this.planOpinion = planOpinion == null ? null : planOpinion.trim();
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId == null ? null : leaderId.trim();
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    public String getLeadAgree() {
        return leadAgree;
    }

    public void setLeadAgree(String leadAgree) {
        this.leadAgree = leadAgree == null ? null : leadAgree.trim();
    }

    public String getLeadOpinion() {
        return leadOpinion;
    }

    public void setLeadOpinion(String leadOpinion) {
        this.leadOpinion = leadOpinion == null ? null : leadOpinion.trim();
    }

    public Date getLeadDate() {
        return leadDate;
    }

    public void setLeadDate(Date leadDate) {
        this.leadDate = leadDate;
    }

    @Override
    public String toString() {
        return "ContractApply{" +
                "id=" + id +
                ", contAppNum='" + contAppNum + '\'' +
                ", statQuote='" + statQuote + '\'' +
                ", topQuote='" + topQuote + '\'' +
                ", lowQuote='" + lowQuote + '\'' +
                ", allSumPrice=" + allSumPrice +
                ", remark='" + remark + '\'' +
                ", authorId='" + authorId + '\'' +
                ", author='" + author + '\'' +
                ", planerId='" + planerId + '\'' +
                ", planer='" + planer + '\'' +
                ", planAgree='" + planAgree + '\'' +
                ", planOpinion='" + planOpinion + '\'' +
                ", planDate=" + planDate +
                ", leaderId='" + leaderId + '\'' +
                ", leader='" + leader + '\'' +
                ", leadAgree='" + leadAgree + '\'' +
                ", leadOpinion='" + leadOpinion + '\'' +
                ", leadDate=" + leadDate +
                '}';
    }
}