package com.shopxx.shopxxhr.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class RespPageBean {

    private Integer total;

    private List<?> data;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public RespPageBean() {
    }
}
