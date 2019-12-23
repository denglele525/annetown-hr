package com.shopxx.shopxxhr.entity;

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
    private Date birthday;
    private String idCard;
    @Enumerated(EnumType.STRING)
    private Wedlock wedlock;
    @ManyToOne(fetch = FetchType.LAZY)
    private Nation nation;
    private String nativePlace;
    private Integer politicId;
    private String email;
    private String phone;
    private String address;
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;
    private Integer joblevelId;
    private Integer posId;
    private String engageForm;
    @Enumerated(EnumType.STRING)
    private TiptopDegree tiptopDegree;
    private String specialty;
    private String school;
    private Date beginDate;
    @Enumerated(EnumType.STRING)
    private WorkState workState;
    private String workId;
    private Double contractTerm;
    private Date conversionTime;
    private Date notworkDate;
    private Date beginContract;
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