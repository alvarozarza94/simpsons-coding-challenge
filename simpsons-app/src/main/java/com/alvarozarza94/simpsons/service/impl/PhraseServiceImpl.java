package com.alvarozarza94.simpsons.service.impl;

import com.alvarozarza94.simpsons.model.api.PhrasePayload;
import com.alvarozarza94.simpsons.model.service.Phrase;
import com.alvarozarza94.simpsons.repository.PhraseRepository;
import com.alvarozarza94.simpsons.service.PhrasesService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PhraseServiceImpl implements PhrasesService {

    @Autowired
    PhraseRepository phraseRepository;

    @Autowired
    private MapperFacade defaultMapper;


    public void setDefaultMapper(MapperFacade defaultMapper) {
        this.defaultMapper = defaultMapper;
    }


    @Override
    public List<Phrase> getPhrases() {
        return defaultMapper.mapAsList(phraseRepository.findAll(), Phrase.class);
    }

    @Override
    public Phrase getPhraseById(String id) {
        com.alvarozarza94.simpsons.model.repository.Phrase phraseRepositoryObject = phraseRepository.findById(id).orElse(null);

        if (phraseRepositoryObject != null) {
            return defaultMapper.map(phraseRepositoryObject, Phrase.class);
        }

        return null;
    }

    @Override
    public Phrase updatePhrase(String id, PhrasePayload phrasePayload) {
        com.alvarozarza94.simpsons.model.repository.Phrase phraseRepositoryObject = phraseRepository.findById(id).orElse(null);

        if (phraseRepositoryObject == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Phrase not found");
        }

        phraseRepositoryObject.setPhrase(phrasePayload.getPhrase());
        return defaultMapper.map(phraseRepository.save(phraseRepositoryObject), Phrase.class);

    }

    @Override
    public void deletePhrase(String id) {

        if (phraseRepository.existsById(id)) {
            phraseRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Phrase not found");
        }
    }
}
