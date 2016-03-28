package com.automation.ivr.store;

import java.net.URI;
import java.net.URISyntaxException;

import com.automation.ivr.exception.VxmlException;


public class DocumentStore {

    private static HttpCaller httpCaller;
    private static String docBaseUrl;

    public DocumentStore() {
        if (httpCaller == null) {
            httpCaller = new HttpCaller();
            httpCaller.startSession();
        }
    }
    public static URI getFullUri(String uri) throws VxmlException {
        URI u = null;
        try {
            u = new URI(uri);
            if (u.getScheme() == null) {
                u = new URI(docBaseUrl + uri);
            }
        } catch (URISyntaxException e) {
            throw new VxmlException(e);
        }
        return u;

    }

    public static String getDocBaseUrl() {
        return docBaseUrl;
    }

    public static void setDocBaseUrl(String docBaseUrl) {
        DocumentStore.docBaseUrl = docBaseUrl;
    }
}
