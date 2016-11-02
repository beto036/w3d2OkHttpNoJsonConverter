package com.example.admin.w3d2okhttpnojsonapi;

import com.orm.SugarRecord;

/**
 * Created by admin on 11/1/2016.
 */

public class User extends SugarRecord{
    private String name;
    private String password;
    private int age;
    private double grade;

    public User(){}

    public User(String name, String password, int age, double grade) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                '}';
    }
}
