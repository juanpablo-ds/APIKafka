package com.prueba.tecnica.model;

import com.prueba.tecnica.model.dto.SearchDTO;
import com.prueba.tecnica.model.dto.SearchExtendedDTO;

import java.sql.Date;

public class SearchDTOAndSearchMapper {



    public Search mapSearchDTOToSearch(final SearchDTO searchDTO){
        var newSearch = new Search(searchDTO.getHotelId(),searchDTO.getCheckIn(),searchDTO.getCheckOut(),searchDTO.getAges());
        newSearch.sortAges(newSearch.getAges());
        return newSearch;
    }

    public SearchDTO mapSearchToSearchDTO(final Search search){
        var newSearch = new SearchDTO(search.getHotelId(),search.getCheckIn(),search.getCheckOut(),search.getAges());
        return newSearch;
    }
}
