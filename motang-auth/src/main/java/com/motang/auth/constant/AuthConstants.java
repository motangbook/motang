package com.motang.auth.constant;

public interface AuthConstants {
    String AllUrl="/**";

    /** oauth2.o接口都以oauth开头*/
    String OAUTH_URL="/oauth/**";

    /** oauth2 请求token的地址*/
    String OAUTH_TOKEN = "/oauth/token";

    /** 认证1模式*/
    String GRANT_TYPE = "grant_type";

    /**密码模式*/
    String PASSWORD = "password";

    /**验证码 值*/
    String VALIDATE_CODE_CODE = "code" ;

    /**验证码 key*/
    String VALIDATE_CODE_KEY = "key";
    String NO_CACHE = "No-cache";
}
