package com.prueba.tecnica.consumers;

import com.prueba.tecnica.model.Search;
import com.prueba.tecnica.model.SearchDTOAndSearchMapper;
import com.prueba.tecnica.service.SearchConsumerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;




    public class SearchConsumerTest {

        @Mock
        private SearchConsumerService searchConsumerService;

        @InjectMocks
        private SearchConsumer searchConsumer;

        @BeforeEach
        public void setup() {

            MockitoAnnotations.initMocks(this);

        }


        @Test
        public void testConsume() {
            // Arrange
            String key = "key";
            Search search = new Search(/* initialize with test data */);
            ConsumerRecord<String, Search> record = new ConsumerRecord<>("topic", 0, 0, key, search);

            Mockito.when(searchConsumer.getSearchConsumerService().save(search)).thenReturn(search);
            // Act
            Search result = searchConsumer.consume(record);

            // Assert

            // Verify that the result is the same search object that was passed as input
            Assertions.assertTrue(search.equals(result));
        }
    }
