package com.alvarozarza94.simpsons.model.service;


import com.alvarozarza94.simpsons.model.api.Phrase;
import lombok.*;

import java.util.List;

@Data
public class Character {

    private String id;
    private String firstName;
    private String lastName;
    private String picture;
    private Integer age;
    List<Phrase> phrases;

}
