package com.gridnine.testing.filterImpl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartureBeforeCurrentPointTimeFilterTest {

    @Test
    void departureBeforeCurrentPointTimeFilterTest() {
        LocalDateTime currentTime = LocalDateTime.now();
        Flight flightBeforeCurrentPointTime = new Flight(Arrays.asList(
                new Segment(currentTime.minusDays(2), currentTime.minusHours(5)),
                new Segment(currentTime.plusDays(1), currentTime.minusDays(3))));
        Flight flightCurrentPointTime = new Flight(Arrays.asList(
                new Segment(currentTime, currentTime.plusHours(7)),
                new Segment(currentTime.plusDays(3), currentTime.plusDays(4))));

        List<Flight> flights = Arrays.asList(flightBeforeCurrentPointTime, flightCurrentPointTime);

        List<Flight> expected = new DepartureBeforeCurrentPointTimeFilter(currentTime).filter(flights);
        List<Flight> result = List.of(flightBeforeCurrentPointTime);
        assertEquals(expected, result);
    }

}