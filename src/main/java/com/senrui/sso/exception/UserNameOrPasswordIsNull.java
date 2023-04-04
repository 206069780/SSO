package com.senrui.sso.exception;

import java.util.Map;

public class UserNameOrPasswordIsNull extends BaseException {
    public UserNameOrPasswordIsNull(Map<String, Object> data) {
        super(ErrorCode.USER_NAME_NOT_FOUND, data);
    }
}
