package com.jsrend.testing;

import java.util.List;

import static com.jsrend.testing.FlightFilterService.*;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        // print all flights
        displayFlights("All flights", flights);

        //Filter 1. Exclude flights until now
        applyAndDisplay(flights, "No flights in the past",  "departureBeforeNow");

        //Filter 2. Exclude segments with arrival before departure
        applyAndDisplay(flights, "Exclude segments with arrival before departure","arrivalBeforeDeparture");

        //Filter 3. Exclude long waits on the ground (>2 hours)
        applyAndDisplay(flights, "No long waits on the ground", "excessiveGround");

        // Thus, rules can be set dynamically.
        // applyAndDisplay(flights, "No long waits on the ground & No flights in the past", "excessiveGround", "departureBeforeNow");
    }
}
