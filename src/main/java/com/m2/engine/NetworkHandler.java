package com.m2.engine;

import com.m2.models.NetworkRequest;
import com.m2.utils.TestLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v118.network.Network;
import org.openqa.selenium.devtools.v118.network.model.Request;
import org.openqa.selenium.devtools.v118.network.model.Response;

import java.util.List;
import java.util.Optional;

public class NetworkHandler {
    private static DevTools devTools;

    public static void initialize(WebDriver driver) {
        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
    }

    public static void captureNetwork(String targetUrl) {
        try {
            devTools.addListener(Network.requestWillBeSent(), requestConsumer -> {
                try {
                    Request request = requestConsumer.getRequest();
                    String requestInfo = "\nMethod: " + request.getMethod()
                            + "\nURL: " + request.getUrl() + "\nRequest Body: " + request.getPostData();

                    String requestId = String.valueOf(requestConsumer.getRequestId());
                    NetworkRequest requestDetails = new NetworkRequest();
                    requestDetails.requestId = requestId;
                    requestDetails.requestInfo = requestInfo;
                    NetworkManager.pushValueToRequestsArray(requestDetails);
                } catch (Exception e) {
                    TestLogger.error("Error capturing request: " + e.getCause());
                }
            });

            devTools.addListener(Network.responseReceived(), responseConsumer -> {
                try {
                    Response response = responseConsumer.getResponse();
                    String responseId = String.valueOf(responseConsumer.getRequestId());
                    String responseUrl = response.getUrl();

                    // Check targetUrl in all matching requests
                    List<NetworkRequest> matchingRequests = NetworkManager.checkMatchingRequests(responseId);
                    for (NetworkRequest matchingRequest : matchingRequests) {
                        if (responseUrl.equals(targetUrl)) {
                            String responseBody = devTools.send(Network.getResponseBody(responseConsumer.getRequestId())).getBody();
                            String responseInfo = "\nStatus: " + response.getStatus() +
                                    "\nResponse: " + responseBody + "\n";
                            String fullInfo = matchingRequest.requestInfo + (responseInfo);
                            TestLogger.info("Target API details fetched --->" + (fullInfo));
                            NetworkManager.matchingAPIExists = true;
                        }
                    }
                } catch (Exception e) {
                    TestLogger.error("Error capturing response: " + e.getCause());
                }
            });
        } catch (Exception e) {
            TestLogger.error("An error occurred while capturing network: " + e.getCause());
        }
    }
}
