package com.example.jobportal;

public class Job {

    private int id;
    private String title;
    private String descj;
    private String req;
    private String salary;


    public Job(int id, String title, String descj, String req, String salary) {
        this.id = id;
        this.title = title;
        this.descj = descj;
        this.req = req;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return descj;
    }

    public String getReq() {
        return req;
    }

    public String getSalary() {
        return salary;
    }

}
