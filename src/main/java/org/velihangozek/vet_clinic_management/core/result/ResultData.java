package org.velihangozek.vet_clinic_management.core.result;

import lombok.Getter;

@Getter
public class ResultData<T> extends Result {
    private final T data;

    public ResultData(boolean success, String message, int httpCode, T data) {
        super(success, message, httpCode);
        this.data = data;
    }
}
