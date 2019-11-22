package com.example.hungrystomach.Model;

public class User {

    private String email;
    private String username;
    private String icon;
    private String phone;
    private String address;
    private String state;
    private String city;
    private String zip;
    private String uid;
    private String full_name;



    private int donut;

    public User(String email, String username, String icon, String phone, String address, String state, String city, String zip, String uid, String full_name, int donut){
        this.email = email;
        this.username = username;
        this.icon = icon;
        this.phone = phone;
        this.address = address;
        this.state = state;
        this.city = city;
        this.zip = zip;
        this.uid = uid;
        this.full_name = full_name;
        this.donut = donut;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUId() {
        return uid;
    }

    public void setUId(String uid) {
        this.uid = uid;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon () {
        return icon;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setAddress(String icon) {
        this.address = icon;
    }

    public String getAddress() {
        return address;
    }

    public void setState(String icon) {
        this.state = icon;
    }

    public String getState() {
        return state;
    }

    public void setCity(String icon) {
        this.city = icon;
    }

    public String getCity() {
        return city;
    }

    public void setZip(String icon) {
        this.zip = icon;
    }

    public String getZip() {
        return zip;
    }

    public void setFull_name(String full_name) {
            this.full_name = full_name;
    }
    public String getFull_name() { return full_name; }


    public int getDonut() {
        return donut;
    }

    public void setDonut(int donut) {
        this.donut = donut;
    }


}
