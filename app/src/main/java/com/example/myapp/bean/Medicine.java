package com.example.myapp.bean;

import java.io.Serializable;

/**
 * Author zhangdongwei
 */
public class Medicine  implements Serializable {
    private String _id;
    private String name;
    private String category;
    private String description;
    private String picId;

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Medicine(String _id, String name, String category, String description,String picId) {
        this._id = _id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.picId=picId;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", picId='" + picId + '\'' +
                '}';
    }
}
