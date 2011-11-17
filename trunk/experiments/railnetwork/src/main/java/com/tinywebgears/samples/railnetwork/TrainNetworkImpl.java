package com.tinywebgears.samples.railnetwork;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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

    @Override
    public Set<Route> getAllRoutes(String source, String destination, Integer minStops, Integer maxStops)
    {
        StationNode sourceNode = stationNodes.get(source);
        Queue<Pair<StationNode, Route>> nodesToProcess = new LinkedList<Pair<StationNode, Route>>();
        Route routeSoFar = new Route(sourceNode.getName());
        nodesToProcess.offer(new Pair<StationNode, Route>(sourceNode, routeSoFar));
        Set<Route> routesFound = new HashSet<Route>();
        checkRoutes(routesFound, nodesToProcess, destination, minStops, maxStops);
        return routesFound;
    }

    private void checkRoutes(Set<Route> routesFound, Queue<Pair<StationNode, Route>> nodesToProcess,
            String destination, Integer minStops, Integer maxStops)
    {
        if (nodesToProcess.isEmpty())
            return;
        Pair<StationNode, Route> node = nodesToProcess.poll();
        StationNode stationNode = node.getFist();
        Route route = node.getSecond();
        logger.debug("Station to check now: " + stationNode + " Route to the station: " + route);
    }
}
