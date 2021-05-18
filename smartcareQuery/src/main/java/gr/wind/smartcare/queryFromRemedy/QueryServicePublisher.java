package gr.wind.smartcare.queryFromRemedy;

import javax.xml.ws.Endpoint;

public class QueryServicePublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:4546/queryservice",
                new QuerySmartcare());
    }
}
