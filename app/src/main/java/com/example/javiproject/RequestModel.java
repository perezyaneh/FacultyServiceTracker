package com.example.javiproject;

public class RequestModel {
    String tupId, eventName, date, status;

    public RequestModel() {
    }

    @Override
    public String toString() {
        return "RequestModel{" +
                "tupId='" + tupId + '\'' +
                ", eventName='" + eventName + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public RequestModel(String tupId, String eventName, String date, String status) {
        this.tupId = tupId;
        this.eventName = eventName;
        this.date = date;
        this.status = status;
    }

    public String getTupId() {
        return tupId;
    }

    public void setTupId(String tupId) {
        this.tupId = tupId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
