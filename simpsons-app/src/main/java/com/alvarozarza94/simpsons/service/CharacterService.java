package com.alvarozarza94.simpsons.service;

import com.alvarozarza94.simpsons.model.api.CharacterPayload;
import com.alvarozarza94.simpsons.model.api.PhrasePayload;
import com.alvarozarza94.simpsons.model.service.Character;
import com.alvarozarza94.simpsons.model.service.Phrase;

import java.util.List;

public interface CharacterService {

    List<Character> getCharacters();

    Character getCharacterById(String id);

    Character addCharacter(CharacterPayload character);

    Character updateCharacter(CharacterPayload character, String id);

    void deleteCharacter(String id);

    List<Phrase> getCharacteresPhrases(String id);

    Phrase addPhraseToCharacter(String id, PhrasePayload phrasePayload);

}
