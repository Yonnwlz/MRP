package com.tuling.entity;

import java.util.List;

/**
 * 分页属性
 */
public class EasyUiDataGrid {
    private Integer total;      //总条数
    private List<?> rows;    //所有信息

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
