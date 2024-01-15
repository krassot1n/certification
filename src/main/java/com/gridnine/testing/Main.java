package com.gridnine.testing;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.filterImpl.ArrivalDateEarlierDepartureFilter;
import com.gridnine.testing.filterImpl.DepartureBeforeCurrentPointTimeFilter;
import com.gridnine.testing.filterImpl.TransferTimeMoreTwoHoursFilter;
import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flight = FlightBuilder.createFlights();

        flight.forEach(System.out::println);

        System.out.println("===============================================================");

        List<Flight> filter1 = new ArrivalDateEarlierDepartureFilter().filter(flight);
        filter1.forEach(System.out::println);

        System.out.println("===============================================================");

        List<Flight> filter2 = new DepartureBeforeCurrentPointTimeFilter(LocalDateTime.now()).filter(flight);
        filter2.forEach(System.out::println);

        System.out.println("===============================================================");

        List<Flight> filter3 = new TransferTimeMoreTwoHoursFilter(2).filter(flight);
        filter3.forEach(System.out::println);
    }
}