package com.alvarozarza94.simpsons.controller;

import com.alvarozarza94.simpsons.model.api.Character;
import com.alvarozarza94.simpsons.model.api.CharacterPayload;
import com.alvarozarza94.simpsons.service.CharacterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/character")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @Autowired
    private MapperFacade defaultMapper;


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "")
    @ApiResponses({ //
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK", response = Character[].class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ERROR", response = ResponseEntity.class), //
    })
    @ApiOperation(value = "Get all the Simpsons characters")
    public List<Character> getCharacters() {
        return defaultMapper.mapAsList(characterService.getCharacters(), Character.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    @ApiResponses({ //
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK", response = Character.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ERROR", response = ResponseEntity.class), //
    })
    @ApiOperation(value = "Get Simpson character by id")
    public Character getCharacterById(@ApiParam(value = "the Simpson character identifier", name = "id", required = true) @PathVariable(value = "id") String id) {
        return defaultMapper.map(characterService.getCharacterById(id), Character.class);


    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, value = "")
    @ApiResponses({ //
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK", response = Character.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ERROR", response = ResponseEntity.class), //

    })
    @ApiOperation(value = "Add a Simpson character")
    public Character addCharacter(@Valid @RequestBody CharacterPayload characterPayload) {

        return defaultMapper.map(characterService.addCharacter(characterPayload), Character.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    @ApiResponses({ //
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK", response = Character.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ERROR", response = ResponseEntity.class), //
    })
    @ApiOperation(value = "Update a Simpson character by id")
    public Character updateCharacter(@Valid @RequestBody CharacterPayload characterPayload,
                                     @ApiParam(value = "the Simpson character identifier", name = "id", required = true) @PathVariable(value = "id") String id) {

        return defaultMapper.map(characterService.updateCharacter(characterPayload, id), Character.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    @ApiResponses({ //
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK", response = void.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ERROR", response = ResponseEntity.class), //
    })
    @ApiOperation(value = "Delete a Simpson character by id")
    public void deleteCharacter(@ApiParam(value = "the Simpson character identifier", name = "id", required = true) @PathVariable(value = "id") String id) {
        characterService.deleteCharacter(id);
    }

}
