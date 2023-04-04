package com.senrui.sso.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {
    private String roleName;
    private String UUID;
    private String descriptions;
    private List<Permission> permissionsList;
}