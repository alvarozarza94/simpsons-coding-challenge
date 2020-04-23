package com.alvarozarza94.simpsons.model.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "phrase", schema = "testdb")
public class Phrase {
    @Id
    @Column(name = "_id")
    private String id;

    @Column(name = "character_fk")
    private String character;

    private String phrase;

}