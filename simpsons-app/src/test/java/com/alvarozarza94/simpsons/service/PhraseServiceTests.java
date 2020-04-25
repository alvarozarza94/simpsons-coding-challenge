package com.alvarozarza94.simpsons.service;


import com.alvarozarza94.simpsons.model.api.PhrasePayload;
import com.alvarozarza94.simpsons.model.repository.Phrase;
import com.alvarozarza94.simpsons.repository.PhraseRepository;
import com.alvarozarza94.simpsons.service.impl.PhraseServiceImpl;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PhraseServiceTests {

    private static final PodamFactory factory = new PodamFactoryImpl();
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    private MapperFacade defaultMapper;
    @InjectMocks
    private PhraseServiceImpl phraseService;

    @Mock
    private PhraseRepository phraseRepository;

    @Before
    public void setUp() {
        defaultMapper = new DefaultMapperFactory.Builder().build().getMapperFacade();
        phraseService.setDefaultMapper(defaultMapper);
    }

    @Test
    public void getPhrasesTest() {

        // Create mocked objects
        List<Phrase> mockedPhrases = factory.manufacturePojo(ArrayList.class, Phrase.class);

        // When
        when(phraseRepository.findAll()).thenReturn(mockedPhrases);
        List<com.alvarozarza94.simpsons.model.service.Phrase> servicePhrase = phraseService.getPhrases();

        // Then
        assertThat(servicePhrase).isNotNull();
        assertThat(servicePhrase.size()).isEqualTo(mockedPhrases.size());
        assertThat(servicePhrase.get(0).getId()).isEqualTo(mockedPhrases.get(0).getId());
    }

    @Test
    public void getPhraseByIdTestOK() {

        // Create mocked objects
        Optional<Phrase> mockedPhrase = Optional.of(factory.manufacturePojo(Phrase.class));

        // When
        given(phraseRepository.findById("random")).willReturn(mockedPhrase);
        com.alvarozarza94.simpsons.model.service.Phrase servicePhrase = phraseService.getPhraseById("random");

        // Then
        assertThat(servicePhrase).isNotNull();
        assertThat(servicePhrase.getId()).isEqualTo(mockedPhrase.get().getId());
    }

    @Test
    public void getPhraseByIdTestKO() {

        com.alvarozarza94.simpsons.model.service.Phrase servicePhrase = phraseService.getPhraseById("random");
        assertThat(servicePhrase).isNull();
    }


    @Test
    public void updatePhraseTestOK() {

        // Create mocked objects
        Optional<Phrase> mockedPhrase = Optional.of(factory.manufacturePojo(Phrase.class));
        Phrase updatedPhrase = factory.manufacturePojo(Phrase.class);
        PhrasePayload phrasePayload = factory.manufacturePojo(PhrasePayload.class);

        // When
        given(phraseRepository.findById("random")).willReturn(mockedPhrase);
        when(phraseRepository.save(any())).thenReturn(updatedPhrase);

        com.alvarozarza94.simpsons.model.service.Phrase servicePhrase = phraseService.updatePhrase("random", phrasePayload);


        // Then
        assertThat(servicePhrase).isNotNull();
    }


    @Test
    public void updatePhraseTestKO() {

        // Expected exception
        expectedEx.expect(ResponseStatusException.class);

        // When
        phraseService.updatePhrase("test", null);
    }

    @Test
    public void deletePhraseTestOK() {

        // When
        when(phraseRepository.existsById(any())).thenReturn(true);

        phraseService.deletePhrase("test");

    }

    @Test
    public void deletePhraseTestKO() {

        // Expected exception
        expectedEx.expect(ResponseStatusException.class);

        // When
        when(phraseRepository.existsById(any())).thenReturn(false);

        phraseService.deletePhrase("test");
    }


}
