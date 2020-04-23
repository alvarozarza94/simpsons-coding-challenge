package com.alvarozarza94.simpsons.model.api;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class PhrasePayload {

    @NotNull(message = "The phrase field is mandatory")
    private String phrase;

}