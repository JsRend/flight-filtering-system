package com.jsrend.testing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlightFilterService {

    public static List<Flight> filterFlights(List<Flight> flights, List<FlightFilter> filters) {
        return flights.parallelStream()
                .filter(flight -> filters.stream().allMatch(filter -> filter.accept(flight)))
                .collect(Collectors.toList());
    }

    public static void displayFlights(String title, List<Flight> flights) {
        System.out.println(title);
        flights.forEach(System.out::println);
        System.out.println();
    }

    public static void applyAndDisplay(List<Flight> flights, String title, String... filters) {
        List<FlightFilter> f = getFilters(filters);
        List<Flight> result = filterFlights(flights, f);
        displayFlights(title, result);
    }

    public static List<FlightFilter> getFilters(String... key) {
        return Arrays.stream(key)
                .map(FlightFilterFactory::getFilter)
                .toList();
    }
}
