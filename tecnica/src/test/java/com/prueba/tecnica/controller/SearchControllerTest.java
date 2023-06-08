package com.prueba.tecnica.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.tecnica.model.Search;
import com.prueba.tecnica.model.SearchDTOAndSearchMapper;
import com.prueba.tecnica.model.dto.SearchDTO;
import com.prueba.tecnica.model.dto.SearchExtendedDTO;
import com.prueba.tecnica.model.dto.SearchIdDTO;
import com.prueba.tecnica.service.SearchProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


class SearchControllerTest {


    @Mock
    private SearchProducerService searchProducerService;

    @InjectMocks
    private SearchController searchController;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void saveSearch_ReturnsOkResponse() throws Exception {
        // Arrange
        int dia = 03;
        int mes = 8;
        int anio = 2022;
        LocalDate fechaLocal = LocalDate.of(anio,mes,dia);
        String searchId = "searchId123";
        SearchDTO searchDto = new SearchDTO("idhotel", fechaLocal,fechaLocal, Arrays.asList(1, 2,3));
        Search search = new Search(searchId,"idhotel", fechaLocal,fechaLocal, Arrays.asList(1, 2,3));
        SearchIdDTO searchIdDto = new SearchIdDTO(searchId);

        Mockito.when(searchController.getSearchProducerService().saveSearch(Mockito.any(SearchDTO.class))).thenReturn(search);

       var result = this.searchController.saveSearch(searchDto);

       assertEquals(HttpStatus.OK,result.getStatusCode());
       assertEquals(searchId, result.getBody().getSearchId());
    }

    @Test
    public void obtenerSearchPorId_ReturnsOkResponse() throws Exception {
        // Arrange
        String searchId = "searchId123";
        int dia = 03;
        int mes = 8;
        int anio = 2022;
        long count = 10L;
        LocalDate fechaLocal = LocalDate.of(anio,mes,dia);
        SearchDTO searchDto = new SearchDTO("idhotel", fechaLocal,fechaLocal, Arrays.asList(1, 2,3));
        SearchExtendedDTO searchExtendedDto = new SearchExtendedDTO(searchId,searchDto,count);

        Mockito. when(searchController.getSearchProducerService().obtenerSearchPorId(searchId)).thenReturn(searchExtendedDto);
        var result = searchController.obtenerSearchPorId(searchId);

        assertTrue(searchExtendedDto.equals(result.getBody()));



    }

}