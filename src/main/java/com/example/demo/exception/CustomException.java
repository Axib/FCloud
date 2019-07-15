package com.example.demo.exception;

public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 3331649306202288443L;
    private int code = 400;

    public CustomException(String msg) {
        super(msg);
    }

    public CustomException(String msg, int code) {
        super(msg);
        this.code = code;
    }

    public CustomException(String msg, Exception e) {
        super(msg, e);
    }

    public CustomException(String msg, Exception e, int code) {
        super(msg, e);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
