package com.example.javiproject;

public class UserModel {
    String firstName;
    String lastName;
    String tupId;
    String password;
    String requestCount;
    String trackerPoints;

    public UserModel() {
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", tupId='" + tupId + '\'' +
                ", password='" + password + '\'' +
                ", requestCount='" + requestCount + '\'' +
                ", trackerPoints='" + trackerPoints + '\'' +
                '}';
    }

    public UserModel(String firstName, String lastName, String tupId, String password, String requestCount, String trackerPoints) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tupId = tupId;
        this.password = password;
        this.requestCount = requestCount;
        this.trackerPoints = trackerPoints;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTupId() {
        return tupId;
    }

    public void setTupId(String tupId) {
        this.tupId = tupId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(String requestCount) {
        this.requestCount = requestCount;
    }

    public String getTrackerPoints() {
        return trackerPoints;
    }

    public void setTrackerPoints(String trackerPoints) {
        this.trackerPoints = trackerPoints;
    }
}
