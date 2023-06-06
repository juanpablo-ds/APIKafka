package com.prueba.tecnica.model;


import jakarta.persistence.*;

import java.sql.Date;
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
    private Date checkIn;
    @Column(name ="checkOut")
    private Date checkOut;
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

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public List<Integer> getAges() {
        return ages;
    }

    public void setAges(List<Integer> ages) {
        Collections.sort(ages);
        this.ages = ages;
    }

    public Search() {

    }

    public Search(String searchId, String hotelId, Date checkIn, Date checkOut, List<Integer> ages) {
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
