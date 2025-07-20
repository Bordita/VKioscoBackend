package com.techlab.backend.dto;


public class ErrorResponseDto {
    
    private boolean success;
    private String message;
    private String error;
    private Object details;
    private long timestamp;
    
    public ErrorResponseDto() {
        this.success = false;
        this.timestamp = System.currentTimeMillis();
    }
    
    public ErrorResponseDto(String message, String error) {
        this();
        this.message = message;
        this.error = error;
    }
    
    public ErrorResponseDto(String message, String error, Object details) {
        this(message, error);
        this.details = details;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getError() {
        return error;
    }
    
    public void setError(String error) {
        this.error = error;
    }
    
    public Object getDetails() {
        return details;
    }
    
    public void setDetails(Object details) {
        this.details = details;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    @Override
    public String toString() {
        return "ErrorResponseDto{" +
                "exito=" + success +
                ", mensaje='" + message + '\'' +
                ", error='" + error + '\'' +
                ", detalles=" + details +
                ", fechaHora=" + timestamp +
                '}';
    }
}
