package com.tinywebgears.samples.simplejavatest;

import java.util.HashMap;
import java.util.Map;

class SameDepthRoutes
{
    private final Map<String, AllPossibleDestinations> destinations;

    SameDepthRoutes()
    {
        destinations = new HashMap<String, AllPossibleDestinations>();
    }

    private AllPossibleDestinations getOrCreatePossibleDestinations(String stopName)
    {
        AllPossibleDestinations possibleDestinations = destinations.get(stopName);
        if (possibleDestinations == null)
        {
            possibleDestinations = new AllPossibleDestinations();
            destinations.put(stopName, possibleDestinations);
        }
        return possibleDestinations;
    }

    public AllPossibleDestinations getDestinations(String stopName)
    {
        return getOrCreatePossibleDestinations(stopName);
    }
}
