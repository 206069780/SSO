package com.senrui.sso.exception;

import java.util.Map;

public class RedisConnectException extends BaseException {
    public RedisConnectException(ErrorCode errorCode, Map<String, Object> data) {
        super(errorCode, data);
    }
}
