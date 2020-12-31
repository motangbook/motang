package com.motang.common.core.entity;

import java.util.HashMap;

/**
 *  @Description 封装401 403统一响应
 *  @author liuhu
 *  @Date 2020/12/31 15:20
 */
public class CommonResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public CommonResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public CommonResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public CommonResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public String getMessage() {
        return String.valueOf(get("message"));
    }

    public Object getData() {
        return get("data");
    }
}
