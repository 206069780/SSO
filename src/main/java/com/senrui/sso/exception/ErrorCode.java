package com.senrui.sso.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    USER_NAME_ALREADY_EXIST(1001, HttpStatus.BAD_REQUEST, "用户名已经存在"),
    Role_NOT_FOUND(1002, HttpStatus.NOT_FOUND, "未找到指定角色"),
    USER_NAME_NOT_FOUND(1003, HttpStatus.NOT_FOUND, "未找到指定用户"),
    VERIFY_JWT_FAILED(1004, HttpStatus.UNAUTHORIZED, "token验证失败"),
    PARAMETER_ERROR(1005, HttpStatus.BAD_REQUEST, "参数错误"),
    GET_UUID_ERROR(1006, HttpStatus.BAD_REQUEST, "无法生成UUID"),
    //Parameter error
    METHOD_ARGUMENT_NOT_VALID(1007, HttpStatus.BAD_REQUEST, "方法参数验证失败"),
    ACCESS_ERROR_CODE(1008, HttpStatus.BAD_REQUEST, "权限不足"),
    REDIS_CONNECTION_ERROR(1008, HttpStatus.BAD_REQUEST, "无法连接redis服务器");

    private final int code;

    private final HttpStatus status;

    private final String message;

    ErrorCode(int code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
