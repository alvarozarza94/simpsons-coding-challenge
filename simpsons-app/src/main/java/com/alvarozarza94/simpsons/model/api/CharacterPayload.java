package com.alvarozarza94.simpsons.model.api;


import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class CharacterPayload {


    @NotNull(message = "The firstName field is mandatory")
    private String firstName;

    @NotNull(message = "The lastName field is mandatory")
    private String lastName;

    @NotNull(message = "The picture field is mandatory")
    private String picture;

    @NotNull(message = "The age field is mandatory")
    private Integer age;

    List<PhrasePayload> phrases;
}
