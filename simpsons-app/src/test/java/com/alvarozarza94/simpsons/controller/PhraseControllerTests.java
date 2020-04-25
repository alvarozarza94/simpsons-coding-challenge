package com.alvarozarza94.simpsons.controller;


import com.alvarozarza94.simpsons.model.api.PhrasePayload;
import com.alvarozarza94.simpsons.model.service.Phrase;
import com.alvarozarza94.simpsons.service.PhrasesService;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PhraseControllerTests {

    private static final PodamFactory factory = new PodamFactoryImpl();

    private MapperFacade defaultMapper;

    @InjectMocks
    private PhraseController phrasesController;

    @Mock
    private PhrasesService phrasesService;

    @Before
    public void setUp() {
        defaultMapper = new DefaultMapperFactory.Builder().build().getMapperFacade();
        phrasesController.setDefaultMapper(defaultMapper);
    }

    @Test
    public void getPhrasesTest() {

        // Create mocked objects
        List<Phrase> mockedPhrases = factory.manufacturePojo(ArrayList.class, Phrase.class);

        // When
        when(phrasesService.getPhrases()).thenReturn(mockedPhrases);
        List<com.alvarozarza94.simpsons.model.api.Phrase> controllerPhrases = phrasesController.getPhrases();

        // Then
        assertThat(controllerPhrases).isNotNull();
        assertThat(controllerPhrases.size()).isEqualTo(mockedPhrases.size());
        assertThat(controllerPhrases.get(0).getId()).isEqualTo(mockedPhrases.get(0).getId());
    }

    @Test
    public void getPhraseByIdTest() {

        // Create mocked objects
        Phrase mockedPhrase = factory.manufacturePojo(Phrase.class);

        // When
        when(phrasesService.getPhraseById(any())).thenReturn(mockedPhrase);
        com.alvarozarza94.simpsons.model.api.Phrase controllerPhrase = phrasesController.getPhraseById("random");

        // Then
        assertThat(controllerPhrase).isNotNull();
        assertThat(controllerPhrase.getId()).isEqualTo(mockedPhrase.getId());
    }

    @Test
    public void updatePhraseTest() {

        // Create mocked objects
        PhrasePayload mockedPayload = factory.manufacturePojo(PhrasePayload.class);
        Phrase mockedPhrase = factory.manufacturePojo(Phrase.class);

        // When
        when(phrasesService.updatePhrase(any(), any())).thenReturn(mockedPhrase);
        com.alvarozarza94.simpsons.model.api.Phrase controllerPhrase = phrasesController.updatePhrase("random", mockedPayload);

        // Then
        assertThat(mockedPhrase).isNotNull();
        assertThat(mockedPhrase.getId()).isEqualTo(controllerPhrase.getId());
    }



    @Test
    public void deletePhraseTest() {

        phrasesController.deletePhrase("test");

    }


}
