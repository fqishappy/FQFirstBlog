package com.fqishappy.handler.exception;

import com.fqishappy.enums.AppHttpCodeEnum;
import lombok.Getter;

/**
 * @author fqishappy
 * @date 2024/9/16 13:52
 */
@Getter
public class SystemException extends RuntimeException {

    private int code;
    private String msg;

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }

}