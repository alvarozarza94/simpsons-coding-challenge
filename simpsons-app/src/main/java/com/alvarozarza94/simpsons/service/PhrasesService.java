package com.alvarozarza94.simpsons.service;

import com.alvarozarza94.simpsons.model.service.Phrase;
import com.alvarozarza94.simpsons.model.api.PhrasePayload;

import java.util.List;

public interface PhrasesService {


    List<Phrase> getPhrases();

    Phrase getPhraseById(String id);

    Phrase updatePhrase(String id, PhrasePayload phrasePayload);

    void deletePhrase(String id);
}
