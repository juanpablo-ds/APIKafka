package com.prueba.tecnica.service;

import com.prueba.tecnica.model.Search;
import com.prueba.tecnica.repository.SearchRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class SearchConsumerServiceTest {



    @Mock
    private SearchRepository searchRepository;

    @InjectMocks
    private SearchConsumerService searchConsumerService;
    @BeforeEach
    public void setup() {

        MockitoAnnotations.initMocks(this);

    }
    @Test
    public void testSave() {
        // Arrange
        Search search = new Search(/* initialize with test data */);
        Mockito.when(searchConsumerService.getSearchRepository().save(search)).thenReturn(search);

        // Act
        Search result = searchConsumerService.save(search);

        // Assert
        // Verify that the searchRepository.save() method is called with the correct search object
        Mockito.verify(searchRepository).save(search);
        // Verify that the result is the same search object that was returned by the searchRepository
        Assertions.assertTrue(search.equals(result));
    }

}