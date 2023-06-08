package com.prueba.tecnica.model.dto;

import java.util.Objects;

public class SearchExtendedDTO {

    private  final String searchId;
    private  final SearchDTO search;
    private final long count;


    public SearchExtendedDTO(String searchId, SearchDTO search, long count) {
        this.searchId = searchId;
        this.search = search;
        this.count = count;
    }




    public String getSearchId() {
        return searchId;
    }



    public SearchDTO getSearch() {
        return search;
    }



    public long getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SearchExtendedDTO myClass = (SearchExtendedDTO) o;
        return count == myClass.count &&
                Objects.equals(searchId, myClass.searchId) &&
                search.equals( myClass.search);
    }
}
