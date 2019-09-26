package com.shopxx.shopxxhr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menu")
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonIgnore
    private String url;
    private String path;
    //    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private String component;
    private String name;

    //    @Column(name = "iconCls")
    private String iconcls;
    //    private String keepAlive;
//    private String requireAuth;
    @JsonIgnore
    private Integer parentid;
    //    @JsonIgnore
//    @OneToMany
    @Transient
    private List<Role> roles;
    @Transient
    private List<Menu> children;
    @Transient
    private MenuMeta meta;

}
