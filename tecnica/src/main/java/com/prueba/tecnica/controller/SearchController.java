package com.prueba.tecnica.controller;


import com.prueba.tecnica.model.Search;
import com.prueba.tecnica.model.dto.SearchDTO;
import com.prueba.tecnica.model.dto.SearchExtendedDTO;
import com.prueba.tecnica.model.dto.SearchIdDTO;
import com.prueba.tecnica.service.SearchProducerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/")
public class SearchController {

    private final SearchProducerService searchProducerService;

    @Autowired
    public SearchController(SearchProducerService searchService) {
        this.searchProducerService = searchService;
    }

    @PostMapping("/search")
    public ResponseEntity<SearchIdDTO> saveSearch(@RequestBody final SearchDTO searchDto) throws ExecutionException, InterruptedException {
        var result = searchProducerService.saveSearch(searchDto);
        return ResponseEntity.ok(new SearchIdDTO(result.getSearchId()));
    }


    @GetMapping("/count")
    public ResponseEntity<SearchExtendedDTO> obtenerSearchPorId(@RequestParam final String searchId) {
        var result = searchProducerService.obtenerSearchPorId(searchId);
        if( result != null ){
            return ResponseEntity.ok(result);

        }else{
            return ResponseEntity.notFound().build();
        }
    }


    public SearchProducerService getSearchProducerService() {
        return searchProducerService;
    }
}
