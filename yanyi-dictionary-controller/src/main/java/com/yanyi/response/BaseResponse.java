package com.yanyi.response;

/**
 * 返回数据基类
 */
public class BaseResponse {
    public enum ResponseCode {
        OK(200),
        ClientErr(400),
        ServerErr(500);

        private Integer code;

        private String message;

        ResponseCode(Integer code) {
            this.code = code;
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

    protected ResponseCode responseCode;

    protected String message;

    protected int code;

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

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
