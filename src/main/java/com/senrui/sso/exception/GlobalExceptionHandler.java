package com.senrui.sso.exception;

import com.senrui.sso.entity.result.Result;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.ConnectException;

@ControllerAdvice
@Slf4j
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handlerNoFoundException(Exception e) {
        //log.error(e.getMessage());
        return Result.error(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        //log.error(e.getMessage());
        return Result.error("数据库中已存在该记录");
    }

    @ExceptionHandler(UsernameIsExitedException.class)
    public Result usernameIsExitedException(UsernameIsExitedException e) {
//        //log.error(e.getMessage());
        return Result.error("用户已经存在");
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        e.printStackTrace();
        return Result.error();
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public Result authenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException authenticationCredentialsNotFoundException) {
        log.info(authenticationCredentialsNotFoundException.getMessage());
        return Result.error("token 缺失");
    }

    @ExceptionHandler(ServiceException.class)
    public Result serviceException(ServiceException e) {
        //log.error(e.getMessage());
        return Result.error();
    }

    @ExceptionHandler(ConnectException.class)
    public Result connectException(ConnectException e) {
        //log.error(e.getMessage());
        return Result.error("系统调用异常");
    }

    @ExceptionHandler(ResourceAccessException.class)
    public Result connectException(ResourceAccessException e) {
        //log.error(e.getMessage());
        return Result.error("系统之间调用异常");
    }

    @ResponseBody
    @ExceptionHandler(value = {ExpiredJwtException.class})
    public Result expiredJwtException(ExpiredJwtException e) {
        //log.error(e.getMessage());
        return Result.error("Token过期");
    }

    @ExceptionHandler(value = UnsupportedJwtException.class)
    @ResponseBody
    public Result unsupportedJwtException(UnsupportedJwtException e) {
        //log.error(e.getMessage());
        return Result.error("Token签名失败");
    }

    @ExceptionHandler(value = SignatureException.class)
    @ResponseBody
    public Result signatureException(SignatureException e) {
        //log.error(e.getMessage());
        return Result.error("Token格式错误");
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public Result illegalArgumentException(IllegalArgumentException e) {
        //log.error(e.getMessage());
        return Result.error("Token非法参数异常");
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseBody
    public Result accessDeniedException(AccessDeniedException e) {
        //log.error(e.getMessage());
        return Result.error("权限不足");
    }

    @ExceptionHandler(value = MalformedJwtException.class)
    @ResponseBody
    public Result malformedJwtException(MalformedJwtException e) {
//        //log.error(e.getMessage());
        return Result.error("Token没有被正确构造");
    }

    @ExceptionHandler(value = UserNameAlreadyExistException.class)
    @ResponseBody
    public Result userNameAlreadyExistException(UserNameAlreadyExistException e) {
//        log.error(e.getData() + "  " + e.getMessage());
        return Result.error(e.getData().get("account") + "账号已存在，请输入其他账号");
    }

    @ExceptionHandler(value = GetUUIDException.class)
    @ResponseBody
    public Result getUUIDException(GetUUIDException e) {
//        //log.error(e.getMessage());
        return Result.error("UUID 服务器异常 , 获取UUID失败");
    }

    @ExceptionHandler(value = RedisConnectException.class)
    @ResponseBody
    public Result redisConnectException(RedisConnectException e) {
        //log.error(e.getMessage());
        return Result.error("redis 服务异常");
    }

    @ExceptionHandler(value = UserNameNotFoundException.class)
    @ResponseBody
    public Result userNameNotFoundException(UserNameNotFoundException e) {
//        //log.error(e.getMessage());
        return Result.error("用户不存在");
    }

    @ExceptionHandler(value = UserNameOrPasswordIsNull.class)
    @ResponseBody
    public Result UserNameOrPasswordIsNull(UserNameOrPasswordIsNull e) {
//        //log.error(e.getMessage());
        return Result.error("账户或密码不能为空");
    }
}
