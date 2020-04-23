package com.alvarozarza94.simpsons.model.repository;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "character", schema = "testdb")
public class Character {
    @Id
    @Column(name = "_id")
    private String id;

    @Column(name = "first_name")
    private String firtsName;

    @Column(name = "last_name")
    private String lastName;

    private String picture;

    private Integer age;

    @OneToMany
    @JoinColumn(name = "character_fk")
    List<Phrase> phrases;

}
