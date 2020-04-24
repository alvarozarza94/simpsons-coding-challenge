package com.alvarozarza94.simpsons.model.service;


import com.alvarozarza94.simpsons.model.api.Phrase;

import java.util.List;

public class Character {

    private String id;
    private String firstName;
    private String lastName;
    private String picture;
    private Integer age;
    List<Phrase> phrases;

    public Character() {
    }

    public Character(String id, String firstName, String lastName, String picture, Integer age, List<Phrase> phrases) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.age = age;
        this.phrases = phrases;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Phrase> getPhrases() {
        return phrases;
    }

    public void setPhrases(List<Phrase> phrases) {
        this.phrases = phrases;
    }
}
