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
        com.alvarozarza94.simpsons.model.repository.Character characterRepositoryObject = characterRepository.findById(id).orElse(null);

        if (characterRepositoryObject != null) {
            return defaultMapper.map(characterRepositoryObject, Character.class);
        }

        return null;
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

        com.alvarozarza94.simpsons.model.repository.Character oldCharacter = getCharacterIfExists(id);

        deleteCharacter(id);

        oldCharacter.setId(id);
        oldCharacter.setFirstName(character.getFirstName());
        oldCharacter.setLastName(character.getLastName());
        oldCharacter.setAge(character.getAge());
        oldCharacter.setPicture(character.getPicture());


        oldCharacter.getPhrases().removeAll(oldCharacter.getPhrases());
        oldCharacter.setPhrases(fillPhrases(character, id));


        return defaultMapper.map(characterRepository.save(oldCharacter), Character.class);
    }


    @Override
    public void deleteCharacter(String id) {

        if (characterRepository.existsById(id)) {
            characterRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found");
        }
    }

    @Override
    public List<com.alvarozarza94.simpsons.model.service.Phrase> getCharacteresPhrases(String id) {

        com.alvarozarza94.simpsons.model.repository.Character characterRepositoryObject = characterRepository.findById(id).orElse(null);

        if (characterRepositoryObject != null && !CollectionUtils.isEmpty(characterRepositoryObject.getPhrases())) {
            return defaultMapper.mapAsList(characterRepositoryObject.getPhrases(), com.alvarozarza94.simpsons.model.service.Phrase.class);
        }

        return new ArrayList<>();
    }

    @Override
    public com.alvarozarza94.simpsons.model.service.Phrase addPhraseToCharacter(String id, PhrasePayload phrasePayload) {

        com.alvarozarza94.simpsons.model.repository.Character oldCharacter = getCharacterIfExists(id);
        String generatedId = RandomStringFactory.createRandomString();

        oldCharacter.getPhrases().add(new Phrase(generatedId, oldCharacter.getId(), phrasePayload.getPhrase()));
        Phrase phrase = characterRepository.save(oldCharacter).getPhrases().stream().filter(x -> x.getId().equals(generatedId)).findFirst().orElse(null);
        return defaultMapper.map(phrase, com.alvarozarza94.simpsons.model.service.Phrase.class);

    }


    private com.alvarozarza94.simpsons.model.repository.Character getCharacterIfExists(String id) {

        com.alvarozarza94.simpsons.model.repository.Character oldCharacter = characterRepository.findById(id).orElse(null);

        if (oldCharacter == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found");
        }
        return oldCharacter;
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
