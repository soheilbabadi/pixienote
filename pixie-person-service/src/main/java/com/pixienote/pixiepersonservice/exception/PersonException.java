package com.pixienote.pixiepersonservice.exception;

import java.io.Serial;

public class PersonException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -2416662069392926161L;

    public PersonException(String message) {
        super(message);
    }

}
