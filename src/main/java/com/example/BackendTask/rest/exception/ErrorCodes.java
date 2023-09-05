package com.example.BackendTask.rest.exception;

public enum ErrorCodes {
    DUPLICATE_RESOURCE("100", "Resource already exists"),
    RELATED_RESOURCE("110","لا يمكن الحذف يوجد بيانات مرتبطه" ),
    DUPLICATE_ROLE("120", "الدور الوظيفى موجود من قبل لهذا المستخدم"),
    DUPLICATE_USERNAME("102", "اسم المستخدم موجود من قبل"),
    DUPLICATE_EMPLOYEE_CODE("130","هذذا الكود موجود بالفعل ");
    private String code;
    private String desc;

    ErrorCodes(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}
