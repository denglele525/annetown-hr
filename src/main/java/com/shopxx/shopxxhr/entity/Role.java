package com.shopxx.shopxxhr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String nameZh;
    @ManyToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<Menu> menu;
    @JsonIgnore
    @ManyToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<Hr> hr;

    public Role(Integer id, String name, String nameZh) {
        this.id = id;
        this.name = name;
        this.nameZh = nameZh;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getMenus() {
        return menu;
    }

    public void setMenus(List<Menu> menus) {
        this.menu = menus;
    }

    public List<Hr> getHr() {
        return hr;
    }

    public void setHr(List<Hr> hr) {
        this.hr = hr;
    }
}