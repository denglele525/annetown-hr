package com.shopxx.shopxxhr.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "menu_role")
@NoArgsConstructor
@AllArgsConstructor
public class MenuRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "menu_id")
    private Integer menuId;
    @Column(name = "role_id")
    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuID() {
        return menuId;
    }

    public void setMenuID(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}