package com.alvarozarza94.simpsons.model.api;



import javax.validation.constraints.NotNull;
import java.util.List;

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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPicture() {
        return picture;
    }

    public Integer getAge() {
        return age;
    }

    public List<PhrasePayload> getPhrases() {
        return phrases;
    }
}
