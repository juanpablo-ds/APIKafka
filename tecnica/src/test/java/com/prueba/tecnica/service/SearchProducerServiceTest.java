package com.prueba.tecnica.service;

import com.prueba.tecnica.model.Search;
import com.prueba.tecnica.model.dto.SearchDTO;
import com.prueba.tecnica.repository.SearchRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;


class SearchProducerServiceTest {

    @Mock
    private SearchRepository searchRepository;

    @Mock
    private KafkaTemplate<String, Search> kafkaTemplate;

    @InjectMocks
    private SearchProducerService searchProducerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @AfterEach
    void tearDown() {
    }

    @Test
    void saveSearch() throws ExecutionException, InterruptedException {
        //arrange
        Long fecha = 1659542400000l; //fecha 03/08/2022
        SearchDTO searchDto = new SearchDTO("idhotel",new Date(fecha),new Date(fecha), Arrays.asList(1, 2,3));


        Search search = new Search("id","idhotel",new Date(fecha),new Date(fecha), Arrays.asList(1, 2,3));

        //mockito
        when(searchProducerService.getSearchRepository().save(any(Search.class))).thenReturn(search);
        when(searchProducerService.getKafkaTemplate().send(anyString(), any())).thenReturn(null);

        //act
        Search result = searchProducerService.saveSearch(searchDto);

        // Verificar que el método searchRepository.save() se haya llamado una vez

        // Verificar que el resultado retornado sea el mismo objeto search
        //assert
        assertTrue(search.equals(result));

    }

}