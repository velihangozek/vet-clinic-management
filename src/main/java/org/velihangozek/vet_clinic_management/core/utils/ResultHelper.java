package org.velihangozek.vet_clinic_management.core.utils;

import org.springframework.data.domain.Page;
import org.velihangozek.vet_clinic_management.core.result.Result;
import org.velihangozek.vet_clinic_management.core.result.ResultData;
import org.velihangozek.vet_clinic_management.dto.response.CursorResponse;
import org.velihangozek.vet_clinic_management.dto.response.customer.CustomerResponse;

public class ResultHelper {

    public static <T> ResultData<T> created(T data) {
        return new ResultData<>(true, Message.CREATED, 201, data);
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(true, Message.OK, 200, data);
    }

    public static Result ok() {
        return new Result(true, Message.OK, 200);
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

    public static <T> ResultData<CursorResponse<T>> cursor(Page<T> pageData) {

        CursorResponse<T> cursor = new CursorResponse<>();
        cursor.setItemList(pageData.getContent());
        cursor.setPageNumber(pageData.getNumber());
        cursor.setPageSize(pageData.getSize());
        cursor.setTotalElements(pageData.getTotalElements());

        return ResultHelper.success(cursor);
    }

}