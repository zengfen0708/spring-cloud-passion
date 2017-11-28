package com.github.zf.base.exception;

/**
 * @author zengfen
 * @date 2017/11/28
 */
public class HttpProcessException extends Exception {
    private static final long serialVersionUID = -5382037403256671006L;


    public HttpProcessException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     */
    public HttpProcessException(String msg) {
        super(msg);
    }

    /**
     * @param message
     * @param e
     */
    public HttpProcessException(String message, Exception e) {
        super(message, e);
    }
}
