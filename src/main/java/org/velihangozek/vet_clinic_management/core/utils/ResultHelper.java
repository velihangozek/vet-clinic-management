package org.velihangozek.vet_clinic_management.core.utils;

import org.velihangozek.vet_clinic_management.core.result.Result;
import org.velihangozek.vet_clinic_management.core.result.ResultData;

public class ResultHelper {

    public static <T> ResultData<T> created(T data) {
        return new ResultData<>(true, Message.CREATED, 201, data);
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(true, Message.OK, 200, data);
    }

    public static <T> ResultData<T> validateError(T data) {
        return new ResultData<>(false, Message.VALIDATE_ERROR, 400, data);
    }

    public static Result notFoundError() {
        return new Result(false, Message.NOT_FOUND, 404);
    }

    public static Result notFoundError(String message) {
        return new Result(false, message, 404);
    }

}