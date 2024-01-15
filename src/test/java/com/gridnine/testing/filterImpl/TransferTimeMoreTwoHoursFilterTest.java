package com.gridnine.testing.filterImpl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransferTimeMoreTwoHoursFilterTest {

    @Test
    void transferTimeMoreTwoHoursFilterTest(){
        LocalDateTime currentTime = LocalDateTime.now();
        Flight flightWithTransferMoreTwoHours = new Flight(Arrays.asList(
                new Segment(currentTime, currentTime.plusHours(5)),
                new Segment(currentTime.plusDays(1), currentTime.plusDays(3))));
        Flight flightWithoutTransferMoreTwoHours = new Flight(Arrays.asList(
                new Segment(currentTime, currentTime.plusHours(7)),
                new Segment(currentTime.plusHours(8), currentTime.plusDays(4))));

        List<Flight> flights = Arrays.asList(flightWithoutTransferMoreTwoHours, flightWithTransferMoreTwoHours);

        List<Flight> expected = new TransferTimeMoreTwoHoursFilter(2).filter(flights);
        List<Flight> result = List.of(flightWithTransferMoreTwoHours);
        assertEquals(expected,result);
    }

}