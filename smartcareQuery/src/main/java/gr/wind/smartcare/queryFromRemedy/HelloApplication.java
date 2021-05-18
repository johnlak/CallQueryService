package gr.wind.smartcare.queryFromRemedy;

import javax.ws.rs.core.Application;

//@ApplicationPath("/api")
public class HelloApplication extends Application {
    public HelloApplication()
    {
        QueryServicePublisher.main(new String[]{});
    }
}