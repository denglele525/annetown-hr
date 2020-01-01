package com.shopxx.shopxxhr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String gender;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date birthday;
    private String idCard;
    @Enumerated(EnumType.STRING)
    private Wedlock wedlock;
    @ManyToOne(fetch = FetchType.LAZY)
    private Nation nation;
    private String nativePlace;
    @ManyToOne(fetch = FetchType.LAZY)
    private Politicsstatus politic;
    private String email;
    private String phone;
    private String address;
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;
    @ManyToOne(fetch = FetchType.LAZY)
    private JobLevel joblevel;
    @ManyToOne(fetch = FetchType.LAZY)
    private Position pos;
    private String engageForm;
    @Enumerated(EnumType.STRING)
    private TiptopDegree tiptopDegree;
    private String specialty;
    private String school;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date beginDate;
    @Enumerated(EnumType.STRING)
    private WorkState workState;
    private Integer workId;
    private Double contractTerm;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date conversionTime;
    @JsonFormat(pattern = "yyyy-MM-d", timezone = "Asia/Shanghai")
    private Date notworkDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date beginContract;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date endContract;
    private Integer workAge;

    @AllArgsConstructor
    @Getter
    public enum Wedlock {
        已婚,
        未婚,
        离异
    }

    @AllArgsConstructor
    @Getter
    public enum TiptopDegree {
        博士,
        硕士,
        本科,
        大专,
        高中,
        初中,
        小学,
        其他
    }

    @AllArgsConstructor
    @Getter
    public enum WorkState {
        在职,
        离职
    }

}