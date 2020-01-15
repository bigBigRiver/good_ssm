package com.good.frame.bean;

public class WebResult<T> {
    private T data;
    private int code;
    private String message;
    public WebResult() {
    }

    public WebResult(T data) {
        this.data = data;
    }
    private enum CodeType {
        ERROR(-1, "失败"),
        SUCCESS(0, "成功");

        private int value;
        private String descript;

        CodeType(int value, String descript) {
            this.value = value;
            this.descript = descript;
        }

        @Override
        public String toString() {
            return this.descript;
        }
    }

    public WebResult success() {
        code = CodeType.SUCCESS.value;
        message = CodeType.SUCCESS.toString();
        return this;
    }

    public WebResult error() {
        code = CodeType.ERROR.value;
        message = CodeType.ERROR.toString();
        return this;
    }

    public WebResult success(String message) {
        code = CodeType.SUCCESS.value;
        this.message = message;
        return this;
    }

    public WebResult error(String message) {
        code = CodeType.ERROR.value;
        this.message = message;
        return this;
    }


    public T getData() {
        return data;
    }

    public WebResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(CodeType code) {
        this.code = code.value;
    }

    public String getMessage() {
        return message;
    }

    public WebResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

}