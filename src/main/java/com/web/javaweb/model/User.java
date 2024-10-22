package com.web.javaweb.model;

import java.time.LocalDateTime;

/**
 * <p>
 *     用户实体类
 * </p>
 * @author jyzxc
 * @since 2024-10-24
 */
public class User {
    private String username;
    private String gender;
    private int age;
    private LocalDateTime registrationTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDateTime getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(LocalDateTime registrationTime) {
        this.registrationTime = registrationTime;
    }
}
