package com.training.HealthcareApp.DTO;

public class AcknowledgementMessage {

    String requestId;
    String Acknowledgement;
    String Message;

    public AcknowledgementMessage(String requestId, String acknowledgement, String message) {
        this.requestId = requestId;
        Acknowledgement = acknowledgement;
        Message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getAcknowledgement() {
        return Acknowledgement;
    }

    public void setAcknowledgement(String acknowledgement) {
        Acknowledgement = acknowledgement;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
