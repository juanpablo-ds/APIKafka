package com.prueba.tecnica.consumers;



import com.prueba.tecnica.model.Search;
import com.prueba.tecnica.service.SearchConsumerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class SearchConsumer {

    private final SearchConsumerService searchConsumerService;

    @Autowired
    public SearchConsumer(SearchConsumerService searchService) {
        this.searchConsumerService = searchService;
    }
    @KafkaListener(topics = "${spring.kafka.topic}",
    containerFactory = "kafkaListenerContainerFactory")
    public Search consume(ConsumerRecord<String, Search> record) {
        Search search = record.value();
        // Procesar el objeto Search recibido desde Kafka
        System.out.println("Objeto Search recibido desde Kafka: " + search);
        // Guardar en la base de datos
        searchConsumerService.save(search);
        // Realizar cualquier otro procesamiento necesario
        // Devolver el objeto Search procesado
        return search;
    }
}