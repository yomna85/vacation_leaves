package com.example.BackendTask.constant;

public enum RequestStatusCodeEnum {

    ACCEPT_REQUEST("ACCEPT_REQUEST"),
    REFUSE_REQUEST("REJECT_REQUEST"),
    NEW_REQUEST("NEW_REQUEST");
    private final String statusCode;

    RequestStatusCodeEnum(final String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

}