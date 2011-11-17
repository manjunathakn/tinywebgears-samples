package com.tinywebgears.samples.railnetwork;

/*
 * Immutable object representing a train route, containing the path and total distance.
 */
class Route
{
    private final Integer numberOfStops;
    private final Integer totalDistance;
    private final Path path;

    Route(String startingStation)
    {
        assert startingStation != null;
        path = new Path().addStation(startingStation);
        totalDistance = 0;
        numberOfStops = 0;
    }

    private Route(Path path, Integer numberOfStops, Integer totalDistance)
    {
        this.path = path;
        this.numberOfStops = numberOfStops;
        this.totalDistance = totalDistance;
    }

    Route addStation(String station, Integer distance)
    {
        Path newPath = path.addStation(station);
        return new Route(newPath, numberOfStops + 1, totalDistance + distance);
    }

    Path getPath()
    {
        return path;
    }

    Integer getNumberOfStops()
    {
        return numberOfStops;
    }

    Integer getTotalDistance()
    {
        return totalDistance;
    }

    String getSource()
    {
        return path.getSource();
    }

    String getDestination()
    {
        return path.getDestination();
    }

    boolean passesThisStation(String station)
    {
        return path.passesThisStation(station);
    }

    // TODO: equals() and hashCode()

    @Override
    public String toString()
    {
        return path.toString() + " [" + totalDistance + "]";
    }
}
