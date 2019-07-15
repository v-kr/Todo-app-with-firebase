package com.example.todolast.Model;

public class Todo {
    String title;
    String desc;
    String id;
    public Todo(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Todo(String title, String desc, String id){
        this.title=title;
        this.desc=desc;
        this.id=id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
