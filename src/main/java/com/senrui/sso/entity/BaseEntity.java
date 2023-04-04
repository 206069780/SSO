package com.senrui.sso.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class BaseEntity {
    private int sort = 10;
    private Date createTime;
    private String createUser;
    private String updateUser;
    private Date UpdateTime;
    private int isDelete;
}
