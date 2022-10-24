package com.blog.sso.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseEntity {
    private String UUID;
    private String permissionName;
    private String descriptions;
    private String actions;
}

