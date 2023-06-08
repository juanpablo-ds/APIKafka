package com.prueba.tecnica.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class SearchDTO {


    private final  String hotelId;
    @JsonFormat(pattern = "dd/MM/yyyy")

    private  final LocalDate checkIn;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private  final LocalDate checkOut;

    private final  List<Integer> ages;

    @JsonCreator
    public SearchDTO( String hotelId, @JsonFormat(pattern = "dd/MM/yyyy") LocalDate checkIn, @JsonFormat(pattern = "dd/MM/yyyy") LocalDate checkOut, List<Integer> ages) {
        this.hotelId = hotelId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.ages = ages;
    }


    public String getHotelId() {
        return hotelId;
    }



    public LocalDate getCheckIn() {
        return checkIn;
    }



    public LocalDate getCheckOut() {
        return checkOut;
    }



    public List<Integer> getAges() {
        return ages;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SearchDTO miClase = (SearchDTO) o;
        return Objects.equals(hotelId, miClase.hotelId) &&
                Objects.equals(checkIn, miClase.checkIn) &&
                Objects.equals(checkOut, miClase.checkOut) &&
                Objects.equals(ages, miClase.ages);
    }


}
