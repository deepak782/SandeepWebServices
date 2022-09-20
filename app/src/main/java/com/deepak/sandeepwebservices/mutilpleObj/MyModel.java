package com.deepak.sandeepwebservices.mutilpleObj;

public class MyModel {
    String country,city,code;

    public MyModel(String country, String city, String code) {
        this.country = country;
        this.city = city;
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
