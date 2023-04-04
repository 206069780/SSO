package com.senrui.sso.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserAndRole extends BaseEntity {
    private String UUID;
    private String userId;
    private String roleId;
}