package com.tinywebgears.samples.railnetwork;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrainNetworkImpl implements TrainNetwork
{
    private final Logger logger = LoggerFactory.getLogger(TrainNetworkImpl.class);

    private Map<String, StationNode> stationNodes;

    public TrainNetworkImpl()
    {
        stationNodes = new HashMap<String, StationNode>();
    }

    private StationNode getOrCreateStation(String station, Boolean create)
    {
        StationNode stationNode = stationNodes.get(station);
        if (stationNode != null)
            return stationNode;
        if (!create)
            return null;
        stationNode = new StationNode(station);
        stationNodes.put(station, stationNode);
        return stationNode;
    }

    @Override
    public StationNode getStation(String station)
    {
        return getOrCreateStation(station, false);
    }

    @Override
    public void addRout(String source, String destination, Integer distance)
    {
        StationNode sourceNode = getOrCreateStation(source, true);
        StationNode destinationNode = getOrCreateStation(destination, true);
        sourceNode.addRoute(destinationNode, distance);
    }

    @Override
    public Integer checkPath(Path path) throws NoRouteException
    {
        logger.debug("checkPath: " + path);
        if (path == null)
            throw new IllegalArgumentException("Path cannot be null.");
        Queue<String> stationsQueue = path.getStations();
        String station = stationsQueue.poll();
        StationNode stationNode = stationNodes.get(station);
        Integer totalDistance = 0;
        totalDistance += checkDistance(stationNode, stationsQueue);
        return totalDistance;
    }

    private Integer checkDistance(StationNode stationNode, Queue<String> stationsQueue) throws NoRouteException
    {
        String destination = stationsQueue.poll();
        if (destination == null)
            return 0;
        Pair<StationNode, Integer> next = stationNode.checkRoute(destination);
        if (next == null)
        {
            logger.info("NO SUCH ROUTE");
            throw new NoRouteException("No route from " + stationNode + " to " + destination);
        }
        return checkDistance(next.getFist(), stationsQueue) + next.getSecond();
    }
}
