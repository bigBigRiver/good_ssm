package com.good.frame.config;

import com.good.frame.annotation.MyConfiguration;
import org.springframework.stereotype.Component;

@Component
@MyConfiguration(value = "config.properties")
public class MyConfig {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
