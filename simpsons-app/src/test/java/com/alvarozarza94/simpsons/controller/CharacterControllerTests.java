package com.alvarozarza94.simpsons.controller;


import com.alvarozarza94.simpsons.model.api.CharacterPayload;
import com.alvarozarza94.simpsons.model.service.Character;
import com.alvarozarza94.simpsons.service.CharacterService;
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
public class CharacterControllerTests {

    private static final PodamFactory factory = new PodamFactoryImpl();

    private MapperFacade defaultMapper;

    @InjectMocks
    private CharacterController characterController;

    @Mock
    private CharacterService characterService;

    @Before
    public void setUp() {
        defaultMapper = new DefaultMapperFactory.Builder().build().getMapperFacade();
        characterController.setDefaultMapper(defaultMapper);
    }

    @Test
    public void getCharactersTest() {

        // Create mocked objects
        List<Character> mockedCharacters = factory.manufacturePojo(ArrayList.class,Character.class);

        // When
        when(characterService.getCharacters()).thenReturn(mockedCharacters);
        List<com.alvarozarza94.simpsons.model.api.Character> controllerCharacters = characterController.getCharacters();

        // Then
        assertThat(controllerCharacters).isNotNull();
        assertThat(controllerCharacters.size()).isEqualTo(mockedCharacters.size());
        assertThat(controllerCharacters.get(0).getId()).isEqualTo(mockedCharacters.get(0).getId());
    }

    @Test
    public void getCharacterByIdTest() {

        // Create mocked objects
        Character mockedCharacter = factory.manufacturePojo(Character.class);

        // When
        when(characterService.getCharacterById(any())).thenReturn(mockedCharacter);
        com.alvarozarza94.simpsons.model.api.Character controllerCharacter = characterController.getCharacterById("random");

        // Then
        assertThat(controllerCharacter).isNotNull();
        assertThat(controllerCharacter.getId()).isEqualTo(controllerCharacter.getId());
    }

    @Test
    public void addCharacterTest() {

        // Create mocked objects
        CharacterPayload mockedPayload = factory.manufacturePojo(CharacterPayload.class);
        Character mockedCharacter = factory.manufacturePojo(Character.class);

        // When
        when(characterService.addCharacter(any())).thenReturn(mockedCharacter);
        com.alvarozarza94.simpsons.model.api.Character controllerCharacter = characterController.addCharacter(mockedPayload);

        // Then
        assertThat(mockedCharacter).isNotNull();
        assertThat(mockedCharacter.getId()).isEqualTo(controllerCharacter.getId());
    }


    @Test
    public void updateCharacterTest() {

        // Create mocked objects
        CharacterPayload mockedPayload = factory.manufacturePojo(CharacterPayload.class);
        Character mockedCharacter = factory.manufacturePojo(Character.class);

        // When
        when(characterService.updateCharacter(any(),any())).thenReturn(mockedCharacter);
        com.alvarozarza94.simpsons.model.api.Character controllerCharacter = characterController.updateCharacter(mockedPayload,"test");

        // Then
        assertThat(mockedCharacter).isNotNull();
        assertThat(mockedCharacter.getId()).isEqualTo(controllerCharacter.getId());
    }

    public void deleteCharacterTest() {

        characterController.deleteCharacter("test");

    }


}
