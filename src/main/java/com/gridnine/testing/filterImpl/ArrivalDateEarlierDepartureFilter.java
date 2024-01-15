package com.gridnine.testing.filterImpl;

import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.model.Flight;

import java.util.List;
import java.util.stream.Collectors;

public class ArrivalDateEarlierDepartureFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().
                        stream().
                        allMatch(segment -> segment.getDepartureDate().isAfter(segment.getArrivalDate())))
                        .collect(Collectors.toList());
    }
}
