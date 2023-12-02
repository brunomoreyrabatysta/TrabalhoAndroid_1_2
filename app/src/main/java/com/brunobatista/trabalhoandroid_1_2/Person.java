package com.brunobatista.trabalhoandroid_1_2;

import kotlinx.coroutines.channels.ActorKt;

public class Person {
    private int id;
    private String name;
    private String email;
    private String phone;

    public Person() {
        this.id = 0;
        this.name = "";
        this.email = "";
        this.phone = "";
    }

    public Person(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Person( String name, String email, String phone) {
        this.id = 0;
        this.name = name;
        this.email = email;
        this.phone = phone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
