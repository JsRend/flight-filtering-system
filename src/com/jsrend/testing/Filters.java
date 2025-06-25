package com.jsrend.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Filters {
    public static class DepartureBeforeNowFilter implements FlightFilter {

        @Override
        public boolean accept(Flight flight) {
            LocalDateTime now = LocalDateTime.now();
            return flight.getSegments().stream()
                    .noneMatch(segment -> segment.getDepartureDate().isBefore(now));
        }
    }


    public static class ArrivalBeforeDepartureFilter implements FlightFilter {

        @Override
        public boolean accept(Flight flight) {
            return flight.getSegments().stream()
                    .noneMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate()));
        }
    }


    public static class ExcessiveGroundFilter implements FlightFilter {

        private static final long MAX_GROUND_MINUTES = 120;

        @Override
        public boolean accept(Flight flight) {
            List<Segment> segments = flight.getSegments();
            if (segments.size() < 2) { return true; };

            long totalGroundMinutes = 0;

            for (int i = 1; i < segments.size(); i++) {
                LocalDateTime prevArrival = segments.get(i-1).getArrivalDate();
                LocalDateTime nextDeparture = segments.get(i).getDepartureDate();

                long groundMinutes = ChronoUnit.MINUTES.between(prevArrival, nextDeparture);
                totalGroundMinutes += groundMinutes;

                if (totalGroundMinutes > MAX_GROUND_MINUTES) {
                    return false;
                }
            }

            return true;
        }
    }
}
