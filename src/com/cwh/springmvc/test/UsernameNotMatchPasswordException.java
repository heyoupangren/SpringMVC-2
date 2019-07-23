package com.cwh.springmvc.test;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Cwh
 * CreateTime 2019/7/23 9:34
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "用户名和密码不匹配")
public class UsernameNotMatchPasswordException extends RuntimeException {

    private static  final long serialVersionUID = 1;
}
