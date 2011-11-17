package com.tinywebgears.samples.railnetwork;

import java.util.List;

public interface NetworkPlanner
{
    boolean isInitialized();

    Integer checkPath(Path path) throws NoRouteException;

    Integer checkPath(String pathString) throws NoRouteException;

    List<Route> getAllRoutes(String source, String destination, Integer minStops, Integer maxStops);
}
