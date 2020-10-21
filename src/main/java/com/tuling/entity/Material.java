package com.tuling.entity;

import java.io.Serializable;

/**
 * 物资信息实体类
 */
public class Material implements Serializable {
    private Long id;                //序号

    private String materialNum;     //物资编码

    private String materialName;    //物资名称

    private String materialUnit;    //计量单位

    /**
     * 物资类别
     * W000-W40：金属模电阻
     * W000-W41：线绕电阻
     * W000-W60：二级管
     * W000-W61：发光器件
     * W000-W62：三级管
     * W000-W64：晶振
     */
    private String materialType;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterialNum() {
        return materialNum;
    }

    public void setMaterialNum(String materialNum) {
        this.materialNum = materialNum == null ? null : materialNum.trim();
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName == null ? null : materialName.trim();
    }

    public String getMaterialUnit() {
        return materialUnit;
    }

    public void setMaterialUnit(String materialUnit) {
        this.materialUnit = materialUnit == null ? null : materialUnit.trim();
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType == null ? null : materialType.trim();
    }
}