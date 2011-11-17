package com.tinywebgears.samples.railnetwork;

import java.util.ArrayList;
import java.util.List;

class Route
{
    private Integer totalDistance;
    private List<StationNode> stationNodes;

    Route(StationNode sourceNode)
    {
        stationNodes = new ArrayList<StationNode>();
        stationNodes.add(sourceNode);
        totalDistance = 0;
    }

    void addStationNode(StationNode nextNode, Integer distance)
    {
        stationNodes.add(nextNode);
        totalDistance += distance;
    }

    Path getPath()
    {
        Path path = new Path();
        for (StationNode stationNode : stationNodes)
            path.addStation(stationNode.getName());
        return path;
    }

    @Override
    public String toString()
    {
        StringBuilder path = new StringBuilder();
        for (StationNode stationNode : stationNodes)
            path.append(stationNode.getName()).append("-");
        path.append(totalDistance);
        return path.toString();
    }
}
