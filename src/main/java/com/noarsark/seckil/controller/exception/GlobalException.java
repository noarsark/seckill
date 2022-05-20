package com.noarsark.seckil.controller.exception;

import com.noarsark.seckil.Result.CodeMsg;
import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }

}
