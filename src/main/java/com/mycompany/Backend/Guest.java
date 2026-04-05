package com.mycompany.Backend;
public class Guest {
    private int id;
    private String name;
    private String phone;
    private String email;

    // Constructor
    public Guest(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // toString for easy printing
    @Override
    public String toString() {
        return "Guest [ID=" + id + ", Name=" + name + ", Phone=" + phone + ", Email=" + email + "]";
    }
}
