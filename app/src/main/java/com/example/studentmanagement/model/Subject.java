package com.example.studentmanagement.model;

public class Subject {

    private int id;
    private String subjectTitle;
    private int numberOfCredit;
    private String time;
    private String place;

    public Subject(int id, String subjectTitle, int numberOfCredit, String time, String place) {
        this.id = id;
        this.subjectTitle = subjectTitle;
        this.numberOfCredit = numberOfCredit;
        this.time = time;
        this.place = place;
    }

    public Subject(String subjectTitle, int numberOfCredit, String time, String place) {
        this.subjectTitle = subjectTitle;
        this.numberOfCredit = numberOfCredit;
        this.time = time;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public int getNumberOfCredit() {
        return numberOfCredit;
    }

    public void setNumberOfCredit(int numberOfCredit) {
        this.numberOfCredit = numberOfCredit;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
