package com.prueba.tecnica.service;

import com.prueba.tecnica.model.Search;
import com.prueba.tecnica.model.SearchDTOAndSearchMapper;
import com.prueba.tecnica.model.dto.SearchDTO;
import com.prueba.tecnica.model.dto.SearchExtendedDTO;
import com.prueba.tecnica.repository.SearchRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.cglib.core.Local;
import org.springframework.kafka.core.KafkaTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import java.sql.Date;
import java.time.LocalDate;
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

    private SearchDTOAndSearchMapper searchMapper;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.initMocks(this);
        searchMapper = new SearchDTOAndSearchMapper();
    }


    @AfterEach
    void tearDown() {
    }

    @Test
    void saveSearch() throws ExecutionException, InterruptedException {
        //arrange
        int dia = 03;
        int mes = 8;
        int anio = 2022;
        LocalDate fechaLocal = LocalDate.of(anio,mes,dia);
        SearchDTO searchDto = new SearchDTO("idhotel", fechaLocal,fechaLocal, Arrays.asList(1, 2,3));


        Search search = new Search("id","idhotel",fechaLocal,fechaLocal, Arrays.asList(1, 2,3));

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


    @Test
    public void testObtenerSearchPorId() {
        // Datos de prueba
        String searchId = "searchId";
        Long countRecords = 1L;
        int dia = 03;
        int mes = 8;
        int anio = 2022;
        LocalDate fechaLocal = LocalDate.of(anio,mes,dia);

        // Mock del repositorio
        when(searchProducerService.getSearchRepository().findCountingMatchingRecords(searchId)).thenReturn(countRecords);

        // Mock del objeto de búsqueda
        Search searchObject = new Search("hotelId", fechaLocal,fechaLocal, Arrays.asList(1,2,3));
        when(searchProducerService.getSearchRepository().findById(searchId)).thenReturn(Optional.of(searchObject));

        // Mock del mapeo de búsqueda a DTO
        SearchDTO searchDTO = new SearchDTO("hotelId", fechaLocal,fechaLocal, Arrays.asList(1,2,3) );
//        when(searchProducerService.getSearchMapper().mapSearchToSearchDTO(searchObject)).thenReturn(searchDTO);

        // Llamada al método a probar
        SearchExtendedDTO result = searchProducerService.obtenerSearchPorId(searchId);

        // Verificación de resultados
        assertNotNull(result);
        assertEquals(searchId, result.getSearchId());
        assertTrue(searchDTO.equals(result.getSearch()));
        assertEquals(countRecords, result.getCount());

        // Verificación de invocaciones de métodos en el repositorio y el mapper

    }


}