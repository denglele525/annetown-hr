package com.shopxx.shopxxhr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "joblevel")
public class JobLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    private TitleLevel titleLevel;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createDate;
    private Boolean enabled;

    @AllArgsConstructor
    @Getter
    public enum TitleLevel {
        正高级,
        副高级,
        中级,
        初级,
        员级
    }

}