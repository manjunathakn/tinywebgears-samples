package com.tinywebgears.samples.simplejavatest;

import java.util.Queue;

public interface TrainNetwork
{
    StationNode getStation(String stationName);

    void addRout(String source, String destination, Integer distance);

    Integer checkPath(Queue<String> path) throws NoRouteException;
}
