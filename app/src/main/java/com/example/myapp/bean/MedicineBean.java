package com.example.myapp.bean;

import java.io.Serializable;

/**
 * Author zhangdongwei
 */
public class MedicineBean implements Serializable {
    private String title;
    private String notmatch;
    private String desc;
    private int picId;

    public MedicineBean(String title, String notmatch, String desc, int picId) {
        this.title = title;
        this.notmatch = notmatch;
        this.desc = desc;
        this.picId = picId;
    }

    public MedicineBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotmatch() {
        return notmatch;
    }

    public void setNotmatch(String notmatch) {
        this.notmatch = notmatch;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }
}
