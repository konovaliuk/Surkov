package com.training.autoproject.dto;

import org.springframework.validation.FieldError;

import java.util.List;

/**
 * Created by Oleg on 16-May-17.
 */
public class JsonResponse {
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    List<FieldError> fieldErrors;
}
