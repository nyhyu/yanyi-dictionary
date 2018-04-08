package com.yanyi.response;

/**
 * 返回数据基类
 */
public class BaseResponse {
    public enum ResponseCode {
        OK("SUCCESS", 200),
        ClientErr("CLIENT ERROR", 400),
        ServerErr("SERVER ERROR", 500);

        private Integer code;

        private String message;

        ResponseCode(String message, Integer code) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    protected String message;

    protected int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BaseResponse(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public BaseResponse() {
    }
}
