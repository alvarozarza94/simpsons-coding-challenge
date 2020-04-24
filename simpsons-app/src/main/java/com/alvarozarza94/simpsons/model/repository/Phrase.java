package com.alvarozarza94.simpsons.model.repository;


import javax.persistence.*;


@Entity
@Table(name = "phrase", schema = "testdb")
public class Phrase {
    @Id
    @Column(name = "_id")
    private String id;

    @Column(name = "character_fk")
    private String character;

    private String phrase;

    public Phrase() {
    }

    public Phrase(String id, String character, String phrase) {
        this.id = id;
        this.character = character;
        this.phrase = phrase;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}