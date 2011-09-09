package com.tinywebgears.simplecharts.client;

import java.util.Date;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>AusEquitiesService</code>.
 */
public interface AusEquitiesServiceAsync
{
    void getPriceInfo(String[] securityCodes, AsyncCallback<Map<Date, Map<String, Double>>> callback)
            throws IllegalArgumentException;
}
