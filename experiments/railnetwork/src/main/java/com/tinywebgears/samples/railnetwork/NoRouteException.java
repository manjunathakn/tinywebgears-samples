package com.tinywebgears.samples.railnetwork;

public class NoRouteException extends Exception
{
    public NoRouteException(String message)
    {
        super(message);
    }

    public NoRouteException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
