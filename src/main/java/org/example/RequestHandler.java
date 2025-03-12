package org.example;

import org.apache.hc.client5.http.classic.methods.HttpGet;
//import org.apache.hc.client5.http.classic.methods.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.commons.io.IOUtils;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import java.util.logging.Level;

import java.io.IOException;

public class RequestHandler {
    private final String targetUrl;

    public RequestHandler(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String fetchPageContent() {
        System.out.println("[+] Sending request to: " + targetUrl);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(targetUrl);
            request.addHeader("User-Agent", "Java-Security-Scanner");

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getCode();

                if (statusCode == 200) {
                    System.out.println("[✔] Response received successfully.");
                    Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, "[-] Failed with HTTP status: " + statusCode);
                    return IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
                }
            }
        } catch (IOException e) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, "[-] Error fetching page: " + e.getMessage());
        }

        return null;
    }
}
