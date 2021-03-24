package com.example.jobportal;

public class API {

    private static final String ROOT_URL = "http://192.168.136.1/JobPortal/MyApi/Api.php?apicall=";

    public static final String URL_CREATE_JOB = ROOT_URL + "createjob";
    public static final String URL_READ_JOBS = ROOT_URL + "showjobs";
    public static final String URL_UPDATE_JOB = ROOT_URL + "updatejob";
    public static final String URL_DELETE_JOB = ROOT_URL + "deletejob&id=";
}

