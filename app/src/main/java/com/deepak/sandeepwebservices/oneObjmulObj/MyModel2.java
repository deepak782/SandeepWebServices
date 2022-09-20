package com.deepak.sandeepwebservices.oneObjmulObj;

public class MyModel2 {
    int ID;
    String title,imageurl,course1,course2;

    public MyModel2(int ID, String title, String imageurl) {
        this.ID = ID;
        this.title = title;
        this.imageurl = imageurl;
    }

    public MyModel2(int ID, String title, String imageurl, String course1, String course2) {
        this.ID = ID;
        this.title = title;
        this.imageurl = imageurl;
        this.course1 = course1;
        this.course2 = course2;
    }

    public String getCourse1() {
        return course1;
    }

    public void setCourse1(String course1) {
        this.course1 = course1;
    }

    public String getCourse2() {
        return course2;
    }

    public void setCourse2(String course2) {
        this.course2 = course2;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
