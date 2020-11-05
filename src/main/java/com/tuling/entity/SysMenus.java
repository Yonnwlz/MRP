package com.tuling.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单信息实体类
 */
public class SysMenus implements Serializable {
    private Long id;            //序号
    @JsonIgnore
    private Long parentId;      //父级序号
    @JsonIgnore
    private Long seq;           //排序

    @JsonProperty(value = "text")
    private String name;        //菜单名称
    @JsonIgnore
    private String tip;         //提示信息
    @JsonIgnore
    private String descn;       //菜单说明
    @JsonIgnore
    private String imageUrl;    //图片地址
    @JsonIgnore
    private String linkUrl;     //链接地址
    @JsonIgnore
    private String target;      //目标窗口
    @JsonIgnore
    private String status;      //是否可用

    private List<SysMenus> children;     //当前所有子节点

    @JsonProperty(value = "attributes")
    private SysMenusAttributes attributes;  //返回json自定义 attributes属性

    public SysMenusAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(SysMenusAttributes attributes) {
        this.attributes = attributes;
    }

    public List<SysMenus> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenus> children) {
        this.children = children;
    }

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip == null ? null : tip.trim();
    }

    public String getDescn() {
        return descn;
    }

    public void setDescn(String descn) {
        this.descn = descn == null ? null : descn.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Override
    public String toString() {
        return "SysMenus{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", seq=" + seq +
                ", name='" + name + '\'' +
                ", tip='" + tip + '\'' +
                ", descn='" + descn + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", target='" + target + '\'' +
                ", status='" + status + '\'' +
                ", children=" + children +
                ", attributes=" + attributes +
                '}';
    }
}