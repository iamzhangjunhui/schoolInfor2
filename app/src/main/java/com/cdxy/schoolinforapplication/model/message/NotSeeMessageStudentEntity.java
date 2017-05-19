package com.cdxy.schoolinforapplication.model.message;

/**
 * Created by huihui on 2016/12/27.
 */

public class NotSeeMessageStudentEntity {
    //姓名、学号、系、班级、电话号码
    private String name;
    private String id;
    private String department;
    private String clazz;
    private String phoneNumber;

    public NotSeeMessageStudentEntity(String name, String id, String department, String clazz, String phoneNumber) {
        this.name = name;
        this.id = id;
        this.department = department;
        this.clazz = clazz;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "NotSeeMessageStudentEntity{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", department='" + department + '\'' +
                ", clazz='" + clazz + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
