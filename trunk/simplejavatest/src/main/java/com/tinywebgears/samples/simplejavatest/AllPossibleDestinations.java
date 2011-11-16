package com.tinywebgears.samples.simplejavatest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class AllPossibleDestinations
{
    private final Map<String, List<Route>> possibleRoutes;

    AllPossibleDestinations()
    {
        possibleRoutes = new HashMap<String, List<Route>>();
    }

    private List<Route> getOrCreateRoutesToGivenStop(String stopName)
    {
        List<Route> routesToStop = possibleRoutes.get(stopName);
        if (routesToStop == null)
        {
            routesToStop = new ArrayList<Route>();
            possibleRoutes.put(stopName, routesToStop);
        }
        return routesToStop;
    }

    public void addRoute(String stopName, Route route)
    {
        List<Route> routesToStop = getOrCreateRoutesToGivenStop(stopName);
        routesToStop.add(route);
    }

    public List<Route> getRoutes(String stopName)
    {
        return getOrCreateRoutesToGivenStop(stopName);
    }
}
