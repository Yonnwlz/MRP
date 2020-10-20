package com.tuling.entity;

/**
 * 菜单信息 返回json 自定义属性实体类
 */
public class SysMenusAttributes {
    private String tip;         //提示信息

    private String descn;       //菜单说明

    private String imageUrl;    //图片地址

    private String linkUrl;     //链接地址

    private String target;      //目标窗口

    private String status;      //是否可用

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getDescn() {
        return descn;
    }

    public void setDescn(String descn) {
        this.descn = descn;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
