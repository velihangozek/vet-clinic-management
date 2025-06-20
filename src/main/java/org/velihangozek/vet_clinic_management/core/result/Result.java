package org.velihangozek.vet_clinic_management.core.result;

import lombok.Getter;

@Getter
public class Result {

    private final boolean success;
    private final String message;
    private final int httpCode;

    public Result(boolean success, String message, int httpCode) {
        this.success = success;
        this.message = message;
        this.httpCode = httpCode;
    }


}
