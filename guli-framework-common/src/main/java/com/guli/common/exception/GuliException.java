package com.guli.common.exception;

import com.guli.common.constants.ResultCodeEnum;
import lombok.Data;

@Data
public class GuliException extends RuntimeException {

    private Integer code;

    /**
     * 接受状态码和消息
     * @param code
     * @param message
     */
    public GuliException(Integer code, String message) {
        super(message);
        this.code=code;
    }
        /**
         * 接收枚举类型
         * @param resultCodeEnum
         */
    public GuliException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

}
