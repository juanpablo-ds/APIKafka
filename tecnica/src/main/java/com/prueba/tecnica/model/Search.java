package com.prueba.tecnica.model;


import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "search", schema = "data")
public class Search {

    @Id
    @Column(name ="searchId")
    private String searchId;
    @Column(name ="hotelId")
    private String hotelId;
    @Column(name ="checkIn")
    private LocalDate checkIn;
    @Column(name ="checkOut")
    private LocalDate checkOut;
    @Column(name ="ages")
    private List<Integer> ages;


    // Getters y setters

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public List<Integer> getAges() {
        return ages;
    }

    public void setAges(List<Integer> ages) {
        sortAges(ages);
        this.ages = ages;

    }

    public void sortAges(List<Integer> ages){
        Collections.sort(ages);
    }

    public Search() {

    }

    public Search(String hotelId, LocalDate checkIn, LocalDate checkOut, List<Integer> ages) {
        this.hotelId = hotelId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.ages = ages;
    }

    public Search(String searchId, String hotelId, LocalDate checkIn, LocalDate checkOut, List<Integer> ages) {
        this.searchId = searchId;
        this.hotelId = hotelId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.ages = ages;
    }

    public void generateSearchID(){
        this.searchId = UUID.randomUUID().toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Search search = (Search) o;
        return
                Objects.equals(hotelId, search.hotelId) &&
                Objects.equals(checkIn, search.checkIn) &&
                Objects.equals(checkOut, search.checkOut) &&
                Objects.equals(ages, search.ages);
    }


}
