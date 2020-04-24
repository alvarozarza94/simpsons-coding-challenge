package com.alvarozarza94.simpsons.service.impl;

import com.alvarozarza94.simpsons.common.utils.RandomStringFactory;
import com.alvarozarza94.simpsons.model.api.CharacterPayload;
import com.alvarozarza94.simpsons.model.api.PhrasePayload;
import com.alvarozarza94.simpsons.model.repository.Phrase;
import com.alvarozarza94.simpsons.model.service.Character;
import com.alvarozarza94.simpsons.repository.CharacterRepository;
import com.alvarozarza94.simpsons.service.CharacterService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    private MapperFacade defaultMapper;

    @Override
    public List<Character> getCharacters() {
        return defaultMapper.mapAsList(characterRepository.findAll(), Character.class);
    }

    @Override
    public Character getCharacterById(String id) {
        Optional<com.alvarozarza94.simpsons.model.repository.Character> characterRepositoryObject = characterRepository.findById(id);
        try {
            com.alvarozarza94.simpsons.model.repository.Character character = characterRepositoryObject.get();
            return defaultMapper.map(character, Character.class);
        } catch (NullPointerException ex) {
            return null;
        }
    }

    @Override
    public Character addCharacter(CharacterPayload character) {

        String generatedId = RandomStringFactory.createRandomString();
        com.alvarozarza94.simpsons.model.repository.Character newCharacter =
                new com.alvarozarza94.simpsons.model.repository.Character(generatedId,
                        character.getFirstName(), character.getLastName(), character.getPicture(), character.getAge(),
                        new ArrayList<>());

        newCharacter.setPhrases(fillPhrases(character, generatedId));

        return defaultMapper.map(characterRepository.save(newCharacter), Character.class);
    }

    @Override
    public Character updateCharacter(CharacterPayload character, String id) {

        Optional<com.alvarozarza94.simpsons.model.repository.Character> oldCharacter = characterRepository.findById(id);

        if (oldCharacter.get() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found");
        }

        com.alvarozarza94.simpsons.model.repository.Character characterValidated = oldCharacter.get();
        deleteCharacter(id);

        characterValidated.setId(id);
        characterValidated.setFirstName(character.getFirstName());
        characterValidated.setLastName(character.getLastName());
        characterValidated.setAge(character.getAge());
        characterValidated.setPicture(character.getPicture());


        characterValidated.getPhrases().removeAll(characterValidated.getPhrases());
        characterValidated.setPhrases(fillPhrases(character, id));


        return defaultMapper.map(characterRepository.save(characterValidated), Character.class);

    }

    @Override
    public void deleteCharacter(String id) {
        characterRepository.deleteById(id);
    }

    private List<Phrase> fillPhrases(CharacterPayload characterPayload, String characterId) {
        List<Phrase> phrases = new ArrayList<>();

        if (!CollectionUtils.isEmpty(characterPayload.getPhrases())) {
            for (PhrasePayload phrasePayload : characterPayload.getPhrases()) {
                phrases.add(new Phrase(RandomStringFactory.createRandomString(), characterId, phrasePayload.getPhrase()));
            }
        }

        return phrases;
    }

    public void setDefaultMapper(MapperFacade defaultMapper) {
        this.defaultMapper = defaultMapper;
    }
}
