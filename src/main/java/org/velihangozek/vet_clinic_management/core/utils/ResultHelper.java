package org.velihangozek.vet_clinic_management.core.utils;

import org.velihangozek.vet_clinic_management.core.result.ResultData;

public class ResultHelper {

    public static <T> ResultData<T> created(T data) {
        return new ResultData<>(true, ResultMessage.CREATED, 201, data);
    }

}