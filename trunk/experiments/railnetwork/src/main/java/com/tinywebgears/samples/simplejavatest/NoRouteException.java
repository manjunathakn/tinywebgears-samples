package com.tinywebgears.samples.simplejavatest;

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
