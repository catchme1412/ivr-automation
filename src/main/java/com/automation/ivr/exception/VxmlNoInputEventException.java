package com.automation.ivr.exception;

import java.util.concurrent.TimeoutException;

public class VxmlNoInputEventException extends Exception {

    public VxmlNoInputEventException(TimeoutException e) {
        super(e);
    }

}
