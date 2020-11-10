package com.tuling.entity;

import java.io.Serializable;
import java.util.Date;

public class Enquire implements Serializable {
    private Long id;

    private String enquireNum;          //询价书编号

    private String enquireName;         //询价书名称

    private String company;             //单位

    private String linkman;             //联系人

    private String address;             //联系地址

    private String phone;               //联系电话

    private String fax;                 //联系传真

    private String zip;                 //邮编

    private String email;               //电子邮箱

    private Date endDate;               //截至时间

    private String remark;              //备注

    private IdMapping idMapping;

    private EnquireDetail enquireDetail;

    public EnquireDetail getEnquireDetail() {
        return enquireDetail;
    }

    public void setEnquireDetail(EnquireDetail enquireDetail) {
        this.enquireDetail = enquireDetail;
    }

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

    public String getEnquireNum() {
        return enquireNum;
    }

    public void setEnquireNum(String enquireNum) {
        this.enquireNum = enquireNum == null ? null : enquireNum.trim();
    }

    public String getEnquireName() {
        return enquireName;
    }

    public void setEnquireName(String enquireName) {
        this.enquireName = enquireName == null ? null : enquireName.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip == null ? null : zip.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return "Enquire{" +
                "id=" + id +
                ", enquireNum='" + enquireNum + '\'' +
                ", enquireName='" + enquireName + '\'' +
                ", company='" + company + '\'' +
                ", linkman='" + linkman + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", zip='" + zip + '\'' +
                ", email='" + email + '\'' +
                ", endDate=" + endDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}