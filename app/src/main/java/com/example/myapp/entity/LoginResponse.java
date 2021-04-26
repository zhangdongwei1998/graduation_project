package com.example.myapp.entity;

/**
 * Author zhangdongwei
 */
public class LoginResponse {

    /**
     * msg : success
     * code : 0
     * expire : 604800
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI2IiwiaWF0IjoxNjE1MzAwNDEyLCJleHAiOjE2MTU5MDUyMTJ9.SjOIE4SFofuy23WEYqH9nojC_CDv0RHGdlB7QSoekmB_bPJOyfwoYXtnT2-CJDDCdWw-5fCT-GmqFqOyQW814Q
     */

    private String msg;
    private int code;
    private int expire;
    private String token;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
