package com.m2.engine;

import com.m2.models.NetworkRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NetworkManager {
    private static final List<NetworkRequest> listOfRequests = new ArrayList<>();
    private static final List<NetworkRequest> listOfUrls = new ArrayList<>();

    public static boolean matchingAPIExists = false;

    public static void pushValueToRequestsArray(NetworkRequest request) {
        listOfRequests.add(request);
    }

    public static void pushValueToUrlArray(NetworkRequest url) {
        listOfUrls.add(url);
    }

    public static List<NetworkRequest> checkMatchingRequests(String responseId) {
        return listOfRequests.stream()
                .filter(c -> c.requestId.equals(responseId)).collect(Collectors.toList());
    }

    public static List<NetworkRequest> checkMatchingUrls(String targetUrl) {
        return listOfUrls.stream()
                .filter(url -> url.equals(targetUrl))
                .collect(Collectors.toList());
    }

}






