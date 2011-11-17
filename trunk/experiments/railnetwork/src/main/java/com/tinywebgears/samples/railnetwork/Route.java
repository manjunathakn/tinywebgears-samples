package com.tinywebgears.samples.railnetwork;

/*
 * Immutable object representing a train route, containing the path and total distance.
 */
class Route
{
    private final Integer length;
    private final Integer totalDistance;
    private final Path path;

    Route(String startingStation)
    {
        assert startingStation != null;
        path = new Path().addStation(startingStation);
        totalDistance = 0;
        length = 0;
    }

    private Route(Path path, Integer length, Integer totalDistance)
    {
        this.path = path;
        this.length = length;
        this.totalDistance = totalDistance;
    }

    Route addStation(String station, Integer distance)
    {
        Path newPath = path.addStation(station);
        return new Route(newPath, length + 1, totalDistance + distance);
    }

    Path getPath()
    {
        return path;
    }

    Integer getLength()
    {
        return length;
    }

    // TODO: equals() and hashCode()

    @Override
    public String toString()
    {
        return path.toString() + ":" + length + ":" + totalDistance;
    }
}
