package com.alvarozarza94.simpsons.model.api;


import javax.validation.constraints.NotNull;


public class PhrasePayload {

    @NotNull(message = "The phrase field is mandatory")
    private String phrase;

    public PhrasePayload(@NotNull(message = "The phrase field is mandatory") String phrase) {
        this.phrase = phrase;
    }

    public PhrasePayload() {
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}