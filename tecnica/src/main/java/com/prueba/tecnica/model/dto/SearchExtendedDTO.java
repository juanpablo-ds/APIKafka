package com.prueba.tecnica.model.dto;

public class SearchExtendedDTO {

    private String searchId;
    private SearchDTO search;
    private long count;


    public SearchExtendedDTO(String searchId, SearchDTO search, long count) {
        this.searchId = searchId;
        this.search = search;
        this.count = count;
    }

    public SearchExtendedDTO() {

    }


    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public SearchDTO getSearch() {
        return search;
    }

    public void setSearch(SearchDTO search) {
        this.search = search;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
