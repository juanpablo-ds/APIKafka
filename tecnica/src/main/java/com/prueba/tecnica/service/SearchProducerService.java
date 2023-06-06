package com.prueba.tecnica.service;


import com.prueba.tecnica.model.Search;
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
    private ModelMapper modelMapper ;
    private final SearchRepository searchRepository;
    private final KafkaTemplate<String, Search> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String KAFKA_TOPIC_ENVIO;
    @Autowired
    public SearchProducerService(SearchRepository searchRepository, KafkaTemplate<String, Search> kafkaTemplate) {
        this.searchRepository = searchRepository;
        this.kafkaTemplate = kafkaTemplate;
        modelMapper = new ModelMapper();
    }



    public Search saveSearch(final SearchDTO searchDto) throws ExecutionException, InterruptedException {
    //validar payload
        //var result = searchRepository.findBySearchParams(search.getHotelId(),search.getCheckIn(),search.getCheckOut(),search.getAges());
        //Search savedSearch = searchRepository.save(search);

        var search = modelMapper.map(searchDto, Search.class);
        search.generateSearchID();

        //searchRepository.save(search);
        var response = kafkaTemplate.send(KAFKA_TOPIC_ENVIO, search); // Publicar el objeto Search en Kafka

        return search;
    }

    public SearchExtendedDTO obtenerSearchPorId(String searchId) {
        SearchExtendedDTO searchExtendedDTO = null;
        var countRecords = searchRepository.findCountingMatchingRecords(searchId);
        if (countRecords>0){
            var searchObject = searchRepository.findById(searchId);
            var searchDTO = modelMapper.map(searchObject.get(), SearchDTO.class);
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
}
