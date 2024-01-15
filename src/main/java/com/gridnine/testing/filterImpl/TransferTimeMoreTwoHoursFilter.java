package com.gridnine.testing.filterImpl;

import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.model.Flight;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TransferTimeMoreTwoHoursFilter implements FlightFilter {
    private final long parkingHours;

    public TransferTimeMoreTwoHoursFilter(long parkingHours) {
        this.parkingHours = parkingHours;
    }

    @Override
    public List<Flight> filter(List<Flight> flights) {

        //Фильтруем полеты без пересадок
        List<Flight> filteredFlights = flights.stream().
                filter(flight -> flight.getSegments().size() > 1).
                collect(Collectors.toList());
        //Возвращаем список полетов с временем на земле больше заданого в кострукторе
        return filteredFlights.stream().
                filter(flight -> calculateParkingTime(flight) >= parkingHours * 60L).
                collect(Collectors.toList());
    }

    private long calculateParkingTime(Flight flight) {
        return IntStream.range(0, flight.getSegments().size() - 1).// Создаем поток индесов, для итерации по сигментам полета
                mapToLong(i -> ChronoUnit.MINUTES.between(//Для каждого индекса i в потоке применяется функция
                flight.getSegments().get(i).getArrivalDate(),//которая вычисляет разницу в минутах между датой прибытия сегмента i
                flight.getSegments().get(i + 1).getDepartureDate())).//и датой отправления следующего сегмента i + 1.
                sum();//Суммирует все значения, полученные в результате предыдущего шага, представляя общее время стоянки в минутах
    }
}
