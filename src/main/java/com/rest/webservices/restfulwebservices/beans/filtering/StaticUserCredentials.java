package com.rest.webservices.restfulwebservices.beans.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StaticUserCredentials {

    private String uuid;
    //Hiding the password to be shown in response....(Static Filtering)
    @JsonIgnore
    private String password;
    private String mobile;

    public StaticUserCredentials() {
    }

    public StaticUserCredentials(String uuid, String password, String mobile) {
        this.uuid = uuid;
        this.password = password;
        this.mobile = mobile;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
