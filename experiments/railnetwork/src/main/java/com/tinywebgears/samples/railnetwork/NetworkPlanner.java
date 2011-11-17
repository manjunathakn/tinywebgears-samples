package com.tinywebgears.samples.railnetwork;

import java.util.Set;

public interface NetworkPlanner
{
    boolean isInitialized();

    Route checkPath(Path path) throws NoRouteException;

    Route checkPath(String pathString) throws NoRouteException;

    Set<Route> getAllRoutes(String source, String destination, Integer minStops, Integer maxStops);

    Set<Route> getAllRoutes(String source, String destination, Integer maxDistance);

    Route getShortestRoute(String source, String destination);
}
