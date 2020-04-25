package com.alvarozarza94.simpsons.controller;

import com.alvarozarza94.simpsons.model.api.Phrase;
import com.alvarozarza94.simpsons.model.api.PhrasePayload;
import com.alvarozarza94.simpsons.service.PhrasesService;
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
@RequestMapping("/api/phrases")
public class PhraseController {

    @Autowired
    private PhrasesService phrasesService;

    @Autowired
    private MapperFacade defaultMapper;


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "")
    @ApiResponses({ //
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK", response = Phrase[].class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ERROR", response = ResponseEntity.class), //
    })
    @ApiOperation(value = "Get all the phrases")
    public List<Phrase> getPhrases() {
        return defaultMapper.mapAsList(phrasesService.getPhrases(), Phrase.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    @ApiResponses({ //
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK", response = Phrase.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ERROR", response = ResponseEntity.class), //
    })
    @ApiOperation(value = "Get phrase by id")
    public Phrase getPhraseById(@ApiParam(value = "the phrase identifier", name = "id", required = true) @PathVariable(value = "id") String id) {
        return defaultMapper.map(phrasesService.getPhraseById(id), Phrase.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    @ApiResponses({ //
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK", response = Phrase.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ERROR", response = ResponseEntity.class), //
    })

    @ApiOperation(value = "Update a phrase")
    public Phrase updatePhrase(@ApiParam(value = "the phrase identifier", name = "id", required = true) @PathVariable(value = "id") String id,//
                                                 @Valid @RequestBody PhrasePayload phrasePayload) {
        return defaultMapper.map(phrasesService.updatePhrase(id, phrasePayload), Phrase.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    @ApiResponses({ //
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK", response = void.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "ERROR", response = ResponseEntity.class), //
    })

    @ApiOperation(value = "Delete a phrase")
    public void deletePhrase(@ApiParam(value = "the phrase identifier", name = "id", required = true) @PathVariable(value = "id") String id) {
        phrasesService.deletePhrase(id);
    }

    public void setDefaultMapper(MapperFacade defaultMapper) {
        this.defaultMapper = defaultMapper;
    }
}
