package com.jnshu.pojo;

import java.io.Serializable;

/**
 * Created by hfismyangel@163.com on 2017/7/5.
 */
public class Developer implements Serializable{
    private int id;
    private String type;
    private String description;
    private int needed;
    private String growup;
    private String pay0_1;
    private String pay1_3;
    private String pay3_5;
    private int studypeople;
    private String tag;
    private String introduce;
    private Long create_at;

    public Developer() {
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", needed=" + needed +
                ", growup='" + growup + '\'' +
                ", pay0_1='" + pay0_1 + '\'' +
                ", pay1_3='" + pay1_3 + '\'' +
                ", pay3_5='" + pay3_5 + '\'' +
                ", studypeople=" + studypeople +
                ", tag='" + tag + '\'' +

                ", introduce='" + introduce + '\'' +
                ", create_at=" + create_at +
                '}';
    }

    public Long getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Long create_at) {
        this.create_at = create_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNeeded() {
        return needed;
    }

    public void setNeeded(int needed) {
        this.needed = needed;
    }

    public String getGrowup() {
        return growup;
    }

    public void setGrowup(String growup) {
        this.growup = growup;
    }

    public String getPay0_1() {
        return pay0_1;
    }

    public void setPay0_1(String pay0_1) {
        this.pay0_1 = pay0_1;
    }

    public String getPay1_3() {
        return pay1_3;
    }

    public void setPay1_3(String pay1_3) {
        this.pay1_3 = pay1_3;
    }

    public String getPay3_5() {
        return pay3_5;
    }

    public void setPay3_5(String pay3_5) {
        this.pay3_5 = pay3_5;
    }

    public int getStudypeople() {
        return studypeople;
    }

    public void setStudypeople(int studyPeople) {
        this.studypeople = studyPeople;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

}
