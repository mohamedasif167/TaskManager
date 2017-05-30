package com.example.a15017498.taskmanager;

import java.io.Serializable;

/**
 * Created by 15017498 on 30/5/2017.
 */

public class Task implements Serializable {

    private int ID;
    private String name;
    private String desc;


    public Task(int id,String name, String desc) {
        this.ID = id;
        this.name = name;
        this.desc = desc;
    }



    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
