package com.tinywebgears.samples.railnetwork;

import java.util.Set;

public interface TrainNetwork
{
    void addRout(String source, String destination, Integer distance);

    Integer checkPath(Path path) throws NoRouteException;

    Set<Route> getAllRoutes(String source, String destination, Integer minStops, Integer maxStops);
}
