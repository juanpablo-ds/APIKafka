package com.prueba.tecnica.repository;

import com.prueba.tecnica.model.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface SearchRepository  extends JpaRepository<Search, String> {
    @Query("SELECT COUNT(s) FROM Search s WHERE s.hotelId = :hotelId " +
            "AND s.checkIn = :checkIn " +
            "AND s.checkOut = :checkOut " +
            "AND s.ages = :ages")
    long countBySearchParams(String hotelId, Date checkIn, Date checkOut, List<Integer> ages);

    @Query("SELECT count(s) FROM Search s WHERE " +
            " (s.hotelId, s.checkIn, s.checkOut, s.ages) IN " +
            "(SELECT s2.hotelId, s2.checkIn, s2.checkOut, s2.ages FROM Search s2 WHERE s2.id = :id)")
    Long findCountingMatchingRecords(@Param("id") String id);


}
