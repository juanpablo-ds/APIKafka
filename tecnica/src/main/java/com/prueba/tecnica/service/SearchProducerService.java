package com.prueba.tecnica.service;


import com.prueba.tecnica.model.Search;
import com.prueba.tecnica.model.SearchDTOAndSearchMapper;
import com.prueba.tecnica.model.dto.SearchDTO;
import com.prueba.tecnica.model.dto.SearchExtendedDTO;
import com.prueba.tecnica.repository.SearchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class SearchProducerService {
    private final SearchDTOAndSearchMapper searchMapper ;
    private final SearchRepository searchRepository;
    private final KafkaTemplate<String, Search> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String KAFKA_TOPIC_ENVIO;
    @Autowired
    public SearchProducerService(SearchRepository searchRepository, KafkaTemplate<String, Search> kafkaTemplate) {
        this.searchRepository = searchRepository;
        this.kafkaTemplate = kafkaTemplate;
        searchMapper = new SearchDTOAndSearchMapper();
    }



    public Search saveSearch(final SearchDTO searchDto) throws ExecutionException, InterruptedException {

        var search = searchMapper.mapSearchDTOToSearch(searchDto);
        search.generateSearchID();

        var response = kafkaTemplate.send(KAFKA_TOPIC_ENVIO, search); // Publicar el objeto Search en Kafka

        return search;
    }

    public SearchExtendedDTO obtenerSearchPorId(String searchId) {
        SearchExtendedDTO searchExtendedDTO = null;
        var countRecords = searchRepository.findCountingMatchingRecords(searchId);
        if (countRecords>0){
            var searchObject = searchRepository.findById(searchId);
            var searchDTO = searchMapper.mapSearchToSearchDTO(searchObject.get());
            searchExtendedDTO = new SearchExtendedDTO(searchId, searchDTO, countRecords);

        }

        return searchExtendedDTO;
    }
    // Otros métodos de servicio según sea necesario

    public SearchRepository getSearchRepository() {
        return searchRepository;
    }

    public KafkaTemplate<String, Search> getKafkaTemplate() {
        return kafkaTemplate;
    }

    public SearchDTOAndSearchMapper getSearchMapper() {
        return searchMapper;
    }
}
