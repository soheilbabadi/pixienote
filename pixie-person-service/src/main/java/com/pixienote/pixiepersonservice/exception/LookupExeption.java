package com.pixienote.pixiepersonservice.exception;

import java.io.Serial;

public class LookupExeption extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -2416662069392926161L;

    public LookupExeption(String message) {
        super(message);
    }

}
