package com.jsrend.testing;

@FunctionalInterface
public interface FlightFilter {
    /**
     * Checks if the flight matches the filter
     *
     * @param flight flight to check
     * @return true - the flight passes the filter (must be included)
     *         false - the flight does NOT pass the filter (must be excluded)
     */
    boolean accept(Flight flight);
}
