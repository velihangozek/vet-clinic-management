package org.velihangozek.vet_clinic_management.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.velihangozek.vet_clinic_management.core.result.Result;
import org.velihangozek.vet_clinic_management.core.result.ResultData;
import org.velihangozek.vet_clinic_management.core.utils.Message;
import org.velihangozek.vet_clinic_management.core.utils.ResultHelper;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<Map<String, String>>> handleValidationErrors(MethodArgumentNotValidException e) {

        Map<String, String> validationErrorList = e
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, Objects.requireNonNull(FieldError::getDefaultMessage)));

        return new ResponseEntity<>(ResultHelper.validateError(validationErrorList), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(NotFoundException e) {

        return new ResponseEntity<>(ResultHelper.notFoundError(e.getMessage()), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Result> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {

        Throwable rootCause = ex.getMostSpecificCause();

        if (rootCause instanceof java.time.format.DateTimeParseException) {
            return new ResponseEntity<>(ResultHelper.validateError(
                    "Invalid format for date-time field. Expected format: yyyy-MM-dd'T'HH:mm:ss (ISO 8601)"
            ), HttpStatus.BAD_REQUEST);
        }

        String message = Message.MALFORMED_OR_INVALID_JSON + rootCause.getMessage();
        return new ResponseEntity<>(ResultHelper.validateError(message), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Result> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        if (ex.getRequiredType() != null && ex.getRequiredType().getSimpleName().equals("LocalDateTime")) {
            String message = String.format(
                    "Invalid date-time format for parameter '%s'. Expected format: yyyy-MM-dd'T'HH:mm:ss (ISO 8601)",
                    ex.getName()
            );
            return new ResponseEntity<>(ResultHelper.validateError(message), HttpStatus.BAD_REQUEST);
        }

        // fallback for other mismatches (optional)
        return new ResponseEntity<>(ResultHelper.validateError("Invalid request parameter."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppointmentConflictException.class)
    public ResponseEntity<Result> handleAppointmentConflictException(AppointmentConflictException ex) {
        String message = Message.APPOINTMENT_CONFLICT + ex.getMessage();
        return new ResponseEntity<>(ResultHelper.validateError(message), HttpStatus.CONFLICT); // 409
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Result> handleMissingRequestParam(MissingServletRequestParameterException ex) {
        String message = String.format("Missing required request parameter: '%s'", ex.getParameterName());
        return new ResponseEntity<>(ResultHelper.validateError(message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VaccineConflictException.class)
    public ResponseEntity<Result> handleVaccineConflict(VaccineConflictException ex) {
        return new ResponseEntity<>(ResultHelper.validateError(ex.getMessage()), HttpStatus.CONFLICT);
    }

}