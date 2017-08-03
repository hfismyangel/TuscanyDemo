package com.jnshu.pojo;

import java.io.Serializable;

/**
 * Created by hfismyangel@163.com on 2017/7/4.
 */
public class Student implements Serializable{
    int id;
    String name;
    String qq;
    String type;
    String graduateschool;
    String dailynote;
    String wish;
    String referee;
    String knowwhere;


    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGraduateschool() {
        return graduateschool;
    }

    public void setGraduateschool(String graduateschool) {
        this.graduateschool = graduateschool;
    }

    public String getDailynote() {
        return dailynote;
    }

    public void setDailynote(String dailynote) {
        this.dailynote = dailynote;
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public String getKnowwhere() {
        return knowwhere;
    }

    public void setKnowwhere(String knowwhere) {
        this.knowwhere = knowwhere;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", qq='" + qq + '\'' +
                ", type='" + type + '\'' +
                ", graduateschool='" + graduateschool + '\'' +
                ", dailynote='" + dailynote + '\'' +
                ", wish='" + wish + '\'' +
                ", referee='" + referee + '\'' +
                ", knowwhere='" + knowwhere + '\'' +
                '}';
    }
}
