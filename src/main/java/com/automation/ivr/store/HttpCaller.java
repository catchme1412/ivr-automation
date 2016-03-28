package com.automation.ivr.store;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpCaller {

    private CloseableHttpClient httpclient;

    public void startSession() {
        httpclient = HttpClients.createDefault();
    }
}
