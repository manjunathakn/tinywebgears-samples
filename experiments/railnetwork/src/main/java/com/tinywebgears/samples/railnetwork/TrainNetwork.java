package com.tinywebgears.samples.railnetwork;

import java.util.Queue;

public interface TrainNetwork
{
    StationNode getStation(String stationName);

    void addRout(String source, String destination, Integer distance);

    Integer checkPath(Queue<String> path) throws NoRouteException;
}
