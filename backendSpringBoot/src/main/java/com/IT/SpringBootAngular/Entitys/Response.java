package com.IT.SpringBootAngular.Entitys;

public class Response {
    private String message;
    private String secondMsg;
    private String thirdMsg;

    public Response() {
    }
    public Response(String message){
        this.message=message;
    }

    public Response(String message, String secondMsg) {
        this.message = message;
        this.secondMsg = secondMsg;
    }

    public Response(String message, String secondMsg, String thirdMsg) {
        this.message = message;
        this.secondMsg = secondMsg;
        this.thirdMsg = thirdMsg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSecondMsg() {
        return secondMsg;
    }

    public void setSecondMsg(String secondMsg) {
        this.secondMsg = secondMsg;
    }

    public String getThirdMsg() {
        return thirdMsg;
    }

    public void setThirdMsg(String thirdMsg) {
        this.thirdMsg = thirdMsg;
    }
}
