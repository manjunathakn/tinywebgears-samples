package com.tinywebgears.samples.railnetwork;

import java.util.Queue;

public interface NetworkPlanner
{
    boolean isInitialized();

    StationNode getStation(String stationName);

    Integer checkPath(Queue<String> path) throws NoRouteException;

    Integer checkPath(String... path) throws NoRouteException;
}
