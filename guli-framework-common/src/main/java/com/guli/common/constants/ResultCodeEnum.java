package com.guli.common.constants;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    SUCCESS(true, 20000,"成功"),

    UNKNOWN_REASON(false, 20001, "未知错误"),

    PARAM_ERROR(false,21002,"参数不正确"),

    FILE_UPLOAD_ERROR(false, 21004, "文件上传错误");;

    private Boolean success;

    private Integer code;

    private String message;

    ResultCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

}
