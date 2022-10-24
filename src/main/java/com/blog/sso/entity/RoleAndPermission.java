package com.blog.sso.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RoleAndPermission extends BaseEntity {
    private String roleId;
    private String permissionId;
    private String UUID;
}