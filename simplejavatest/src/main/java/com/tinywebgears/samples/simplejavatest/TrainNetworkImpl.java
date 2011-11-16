package com.tinywebgears.samples.simplejavatest;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrainNetworkImpl implements TrainNetwork
{
    private final Logger logger = LoggerFactory.getLogger(TrainNetworkImpl.class);

    private Map<String, StationNode> stations;

    public TrainNetworkImpl()
    {
        stations = new HashMap<String, StationNode>();
    }

    private StationNode getOrCreateStation(String stationName, Boolean create)
    {
        StationNode station = stations.get(stationName);
        if (station != null)
            return station;
        if (!create)
            return null;
        station = new StationNode(stationName);
        stations.put(stationName, station);
        return station;
    }

    @Override
    public StationNode getStation(String stationName)
    {
        return getOrCreateStation(stationName, false);
    }

    @Override
    public void addRout(String source, String destination, Integer distance)
    {
        StationNode sourceStation = getOrCreateStation(source, true);
        StationNode destinationStation = getOrCreateStation(destination, true);
        sourceStation.addRoute(destinationStation, distance);
    }

    @Override
    public Integer checkPath(Queue<String> path) throws NoRouteException
    {
        logger.debug("checkPath: " + path);
        String stationName = path.poll();
        StationNode station = stations.get(stationName);
        Integer totalDistance = 0;
        totalDistance += checkDistance(station, path);
        return totalDistance;
    }

    private Integer checkDistance(StationNode station, Queue<String> path) throws NoRouteException
    {
        String destinationStationName = path.poll();
        if (destinationStationName == null)
            return 0;
        Pair<StationNode, Integer> next = station.checkRoute(destinationStationName);
        if (next == null)
            throw new NoRouteException("No route from " + station + " to " + destinationStationName);
        return checkDistance(next.getFist(), path) + next.getSecond();
    }
}
