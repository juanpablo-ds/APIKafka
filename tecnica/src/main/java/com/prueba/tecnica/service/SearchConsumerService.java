package com.prueba.tecnica.service;



import com.prueba.tecnica.model.Search;
import com.prueba.tecnica.repository.SearchRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class SearchConsumerService {

    private final SearchRepository searchRepository;

    @Autowired
    public SearchConsumerService(SearchRepository searchService) {
        this.searchRepository = searchService;
    }

    public Search save(Search search) {
        System.out.println("Objeto Search recibido desde Kafka: " + search);
        // Guardar en la base de datos
        var result = searchRepository.save(search);
        return result;

    }
}