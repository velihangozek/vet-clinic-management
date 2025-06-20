package org.velihangozek.vet_clinic_management.core.utils;

import org.velihangozek.vet_clinic_management.core.result.ResultData;

public class ResultHelper {

    public static <T> ResultData<T> created(T data) {
        return new ResultData<>(true, ResultMessage.CREATED, 201, data);
    }

    public static <T> ResultData<T> validateError(T data) {
        return new ResultData<>(false, ResultMessage.VALIDATE_ERROR, 400, data);
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(true, ResultMessage.OK, 200, data);
    }
}