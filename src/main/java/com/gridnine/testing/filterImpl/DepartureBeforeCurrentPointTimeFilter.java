package com.gridnine.testing.filterImpl;

import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DepartureBeforeCurrentPointTimeFilter implements FlightFilter {

    private final LocalDateTime localDateTime;

    public DepartureBeforeCurrentPointTimeFilter(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream().
                filter(flight -> flight.getSegments().
                        stream().
                        limit(1).
                        allMatch(segment -> segment.getDepartureDate().isBefore(localDateTime))).
                collect(Collectors.toList());
    }
}
