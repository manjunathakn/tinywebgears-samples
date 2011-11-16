package com.tinywebgears.samples.simplejavatest;

import java.util.Queue;

public interface NetworkPlanner
{
    boolean isInitialized();

    StationNode getStation(String stationName);

    Integer checkPath(Queue<String> path) throws NoRouteException;
}
