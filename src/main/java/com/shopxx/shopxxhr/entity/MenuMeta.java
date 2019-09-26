package com.shopxx.shopxxhr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuMeta implements Serializable {

    private boolean keepAlive;
    private boolean requireAuth;

}