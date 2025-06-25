package com.jsrend.testing;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FlightFilterFactory {

    private static final Map<String, Supplier<FlightFilter>> FILTERS = new HashMap<>();

    static {
        FILTERS.put("departureBeforeNow", Filters.DepartureBeforeNowFilter::new);
        FILTERS.put("arrivalBeforeDeparture", Filters.ArrivalBeforeDepartureFilter::new);
        FILTERS.put("excessiveGround", Filters.ExcessiveGroundFilter::new);
    }

    public static FlightFilter getFilter(String key) {
        Supplier<FlightFilter> supplier = FILTERS.get(key);
        if (supplier == null) {
            throw new IllegalArgumentException("Unknown filter: " + key);
        }
        return supplier.get();
    }
}
