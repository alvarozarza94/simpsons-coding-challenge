package com.alvarozarza94.simpsons.model.repository;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "character_fk")
    List<Phrase> phrases;

}
