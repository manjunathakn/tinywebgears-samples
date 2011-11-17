package com.tinywebgears.samples.railnetwork;

public interface TrainNetwork
{
    StationNode getStation(String station);

    void addRout(String source, String destination, Integer distance);

    Integer checkPath(Path path) throws NoRouteException;
}
