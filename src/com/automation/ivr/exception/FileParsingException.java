package com.automation.ivr.exception;

public class FileParsingException extends Exception {

    private static final long serialVersionUID = 1L;
    private String msg;
    private Exception exception;

    public FileParsingException(String msg, Exception exception) {
        super(msg, exception);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

}
