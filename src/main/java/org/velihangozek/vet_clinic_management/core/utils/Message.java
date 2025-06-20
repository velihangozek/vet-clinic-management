package org.velihangozek.vet_clinic_management.core.utils;

public class Message {
    public static final String CREATED = "Record Added";
    public static final String OK = "Transaction Successful";
    public static final String VALIDATE_ERROR = "Validation Error";
    public static final String NOT_FOUND = "Record Not Found";

    public static String notFound(String entity, Object id) {
        return entity + " with id " + id + " not found";
    }
}