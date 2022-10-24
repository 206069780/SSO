package com.blog.sso.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class ResponseEntity implements Serializable {
    public static final Long serialVersionUID = 778595113957085184L;
    private int status = 200;
    private String message = "susses";
    private Object data;
    private String description;
    private int code = 1;

    public ResponseEntity(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseEntity() {
    }
}
