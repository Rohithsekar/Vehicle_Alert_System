package com.vehicle_alert.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse {

    private String status;
    private String message;
    private Object data;

    public APIResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
