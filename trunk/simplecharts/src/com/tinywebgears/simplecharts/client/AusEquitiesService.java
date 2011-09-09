package com.tinywebgears.simplecharts.client;

import java.util.Date;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("auseq")
public interface AusEquitiesService extends RemoteService
{
    Map<Date, Map<String, Double>> getPriceInfo(String... securityCodes) throws IllegalArgumentException;
}
