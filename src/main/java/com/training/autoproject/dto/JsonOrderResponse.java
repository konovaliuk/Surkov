package com.training.autoproject.dto;

import com.training.autoproject.entity.Application;

import java.util.List;

/**
 * Created by Oleg on 16-May-17.
 */
public class JsonOrderResponse {
    String status;
    List<Application> applicationList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Application> getApplicationList() {
        return applicationList;
    }

    public void setApplicationList(List<Application> applicationList) {
        this.applicationList = applicationList;
    }
}
