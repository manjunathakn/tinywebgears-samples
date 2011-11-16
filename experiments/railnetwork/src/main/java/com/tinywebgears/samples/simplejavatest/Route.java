package com.tinywebgears.samples.simplejavatest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Route
{
    private Integer totalDistance;
    private List<StationNode> stations;

    Route(StationNode sourceStation)
    {
        stations = new ArrayList<StationNode>();
        stations.add(sourceStation);
        totalDistance = 0;
    }

    void addStation(StationNode nextStation, Integer distance)
    {
        stations.add(nextStation);
        totalDistance += distance;
    }

    Queue<String> getPath()
    {
        Queue<String> path = new LinkedList<String>();
        for (StationNode station : stations)
            path.offer(station.getName());
        return path;
    }

    @Override
    public String toString()
    {
        StringBuilder path = new StringBuilder();
        for (StationNode station : stations)
            path.append(station.getName()).append("-");
        path.append(totalDistance);
        return path.toString();
    }
}
