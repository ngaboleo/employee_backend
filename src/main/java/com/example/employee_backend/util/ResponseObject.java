package com.example.employee_backend.util;

public class ResponseObject {
    private Boolean status;
    private Object result;
    private String message;
    private Long count;

    public ResponseObject(Object object)
    {
        this.status=true;
        this.result=object;
        this.message="Request processed successful";
    }

    public ResponseObject(Exception exception)
    {
        this.status=false;
        this.result=exception.getStackTrace();
        this.message= exception.getMessage();

    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
