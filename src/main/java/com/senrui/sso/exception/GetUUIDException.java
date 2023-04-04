package com.senrui.sso.exception;

import java.util.Map;

public class GetUUIDException extends BaseException {
    public GetUUIDException(ErrorCode errorCode, Map<String, Object> data) {
        super(errorCode, data);
    }
}
