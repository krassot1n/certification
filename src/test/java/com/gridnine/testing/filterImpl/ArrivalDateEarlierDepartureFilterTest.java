package com.gridnine.testing.filterImpl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class ArrivalDateEarlierDepartureFilterTest {

    @Test
    void arrivalDateEarlierDepartureFilterTest(){
        LocalDateTime currentTime = LocalDateTime.now();
        Flight flightWithArrivalBeforeDeparture = new Flight(Arrays.asList(
                new Segment(currentTime, currentTime.minusHours(5)),
                new Segment(currentTime.plusDays(1), currentTime.minusDays(3))));
        Flight flightWithoutArrivalBeforeDeparture = new Flight(Arrays.asList(
                new Segment(currentTime, currentTime.plusHours(7)),
                new Segment(currentTime.plusDays(3), currentTime.plusDays(4))));

        List<Flight> flights = Arrays.asList(flightWithArrivalBeforeDeparture, flightWithoutArrivalBeforeDeparture);

        List<Flight> expected = new ArrivalDateEarlierDepartureFilter().filter(flights);
        List<Flight> result = List.of(flightWithArrivalBeforeDeparture);
        Assertions.assertEquals(expected,result);
    }

}