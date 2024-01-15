package com.gridnine.testing.filterImpl;

import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.model.Flight;

import java.util.List;
import java.util.stream.Collectors;

public class ArrivalDateEarlierDepartureFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().                  //Фильтрация потока полетов. Оставляются только те полеты,
                        stream().                                        //у которых дата отправления каждого сегмента
                        allMatch(segment -> segment.getDepartureDate().  //следует после даты прибытия этого же сегмента
                                isAfter(segment.getArrivalDate())))
                .collect(Collectors.toList());

    }
}
