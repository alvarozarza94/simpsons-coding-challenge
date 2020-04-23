package com.alvarozarza94.simpsons.model.api;

import lombok.Data;

import java.util.List;

@Data
public class Character {

    private String id;
    private String firtsName;
    private String lastName;
    private String picture;
    private Integer age;
    List<Phrase> phrases;


}
