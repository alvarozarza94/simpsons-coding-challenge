package com.alvarozarza94.simpsons.service;


import com.alvarozarza94.simpsons.model.api.CharacterPayload;
import com.alvarozarza94.simpsons.model.api.PhrasePayload;
import com.alvarozarza94.simpsons.model.repository.Character;
import com.alvarozarza94.simpsons.model.repository.Phrase;
import com.alvarozarza94.simpsons.repository.CharacterRepository;
import com.alvarozarza94.simpsons.service.impl.CharacterServiceImpl;
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
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CharacterServiceTests {

    private static final PodamFactory factory = new PodamFactoryImpl();
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    private MapperFacade defaultMapper;
    @InjectMocks
    private CharacterServiceImpl characterService;

    @Mock
    private CharacterRepository characterRepository;

    @Before
    public void setUp() {
        defaultMapper = new DefaultMapperFactory.Builder().build().getMapperFacade();
        characterService.setDefaultMapper(defaultMapper);
    }

    @Test
    public void getCharactersTest() {

        // Create mocked objects
        List<Character> mockedCharacters = factory.manufacturePojo(ArrayList.class, Character.class);

        // When
        when(characterRepository.findAll()).thenReturn(mockedCharacters);
        List<com.alvarozarza94.simpsons.model.service.Character> serviceCharacter = characterService.getCharacters();

        // Then
        assertThat(serviceCharacter).isNotNull();
        assertThat(serviceCharacter.size()).isEqualTo(mockedCharacters.size());
        assertThat(serviceCharacter.get(0).getId()).isEqualTo(mockedCharacters.get(0).getId());
    }

    @Test
    public void getCharacterByIdTestOK() {

        // Create mocked objects
        Optional<Character> mockedCharacter = Optional.of(factory.manufacturePojo(Character.class));

        // When
        given(characterRepository.findById("random")).willReturn(mockedCharacter);
        com.alvarozarza94.simpsons.model.service.Character serviceCharacter = characterService.getCharacterById("random");

        // Then
        assertThat(serviceCharacter).isNotNull();
        assertThat(serviceCharacter.getId()).isEqualTo(mockedCharacter.get().getId());
    }

    @Test
    public void getCharacterByIdTestKO() {

        com.alvarozarza94.simpsons.model.service.Character serviceCharacter = characterService.getCharacterById("random");
        assertThat(serviceCharacter).isNull();
    }

    @Test
    public void addCharacterTest() {

        // Create mocked objects
        CharacterPayload mockedPayload = factory.manufacturePojo(CharacterPayload.class);
        Character mockedCharacter = factory.manufacturePojo(Character.class);

        // When
        when(characterRepository.save(any())).thenReturn(mockedCharacter);
        com.alvarozarza94.simpsons.model.service.Character serviceCharacter = characterService.addCharacter(mockedPayload);

        // Then
        assertThat(mockedCharacter).isNotNull();
        assertThat(mockedCharacter.getId()).isEqualTo(serviceCharacter.getId());
    }


    @Test
    public void updateCharacterTestOK() {

        // Create mocked objects
        CharacterPayload mockedPayload = factory.manufacturePojo(CharacterPayload.class);
        Character mockedCharacter = factory.manufacturePojo(Character.class);
        Character mockedUpdatedCharacter = factory.manufacturePojo(Character.class);

        // When
        when(characterRepository.existsById(any())).thenReturn(true);
        when(characterRepository.findById("random")).thenReturn(Optional.ofNullable(mockedCharacter));
        when(characterRepository.save(any())).thenReturn(mockedUpdatedCharacter);
        com.alvarozarza94.simpsons.model.service.Character serviceCharacter = characterService.updateCharacter(mockedPayload, "random");

        // Then
        assertThat(mockedUpdatedCharacter).isNotNull();
        assertThat(mockedUpdatedCharacter.getId()).isEqualTo(serviceCharacter.getId());
    }



    @Test
    public void updateCharacterTestKO() {

        // Expected exception
        expectedEx.expect(ResponseStatusException.class);

        // When
        characterService.updateCharacter(new CharacterPayload(), "test");
    }

    @Test
    public void deleteCharacterTestOK() {

        // When
        when(characterRepository.existsById(any())).thenReturn(true);

        characterService.deleteCharacter("test");

    }

    @Test
    public void deleteCharacterTestKO() {

        // Expected exception
        expectedEx.expect(ResponseStatusException.class);

        // When
        when(characterRepository.existsById(any())).thenReturn(false);

        characterService.deleteCharacter("test");
    }


    @Test
    public void getCharacterPhrasesTest() {

        // Create mocked objects
        Optional<Character> mockedCharacter = Optional.of(factory.manufacturePojo(Character.class));

        // When
        given(characterRepository.findById(any())).willReturn(mockedCharacter);

        List<com.alvarozarza94.simpsons.model.service.Phrase> servicePhrases = characterService.getCharacteresPhrases(any());

        // Then
        assertThat(servicePhrases).isNotNull();
        assertThat(mockedCharacter.get().getPhrases().size()).isEqualTo(servicePhrases.size());
        assertThat(mockedCharacter.get().getPhrases().get(0).getId()).isEqualTo(servicePhrases.get(0).getId());
    }

    @Test
    public void getCharacterPhrasesEmptyTest() {

        List<com.alvarozarza94.simpsons.model.service.Phrase> servicePhrases = characterService.getCharacteresPhrases(any());

        // Then
        assertThat(servicePhrases.size()).isEqualTo(0);
    }

    @Test
    public void addPhraseToCharacterTestOK() {

        // Create mocked objects
        Optional<Character> mockedCharacter = Optional.of(factory.manufacturePojo(Character.class));

        // When
        given(characterRepository.findById("random")).willReturn(mockedCharacter);
        when(characterRepository.save(any())).thenReturn(factory.manufacturePojo(Character.class));

        com.alvarozarza94.simpsons.model.service.Phrase servicePhrase = characterService.addPhraseToCharacter("random",new PhrasePayload("test"));
    }


    @Test
    public void addPhraseToCharacterTestKO() {

        // Expected exception
        expectedEx.expect(ResponseStatusException.class);

        // When
        characterService.addPhraseToCharacter("id",new PhrasePayload());
    }
}
