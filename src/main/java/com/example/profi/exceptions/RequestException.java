package com.example.profi.exceptions;

import javax.servlet.ServletException;

public class RequestException extends ServletException {
    private int code;

    public RequestException() {
        super();
    }

    public RequestException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
