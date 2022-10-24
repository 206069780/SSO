package com.blog.sso.exception;

import java.util.Map;

public class ParameterException extends BaseException {

    public ParameterException(ErrorCode errorCode, Map<String, Object> data) {
        super(errorCode, data);
    }
}
