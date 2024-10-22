package com.web.javaweb.model;

public class Student {
    private String id;
    private String name;
    private String major;
    private double gradeMidterm;
    private double gradeFinal;
    private double totalGrade;

    public Student(String id, String name, String major, double gradeMidterm, double gradeFinal, double totalGrade) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.gradeMidterm = gradeMidterm;
        this.gradeFinal = gradeFinal;
        this.totalGrade = totalGrade;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public double getGradeMidterm() {
        return gradeMidterm;
    }

    public void setGradeMidterm(double gradeMidterm) {
        this.gradeMidterm = gradeMidterm;
    }

    public double getGradeFinal() {
        return gradeFinal;
    }

    public void setGradeFinal(double gradeFinal) {
        this.gradeFinal = gradeFinal;
    }

    public double getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(double totalGrade) {
        this.totalGrade = totalGrade;
    }
}