package org.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ScannerEngine {
    private final String targetUrl;

    public ScannerEngine(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public void startScan() {
        System.out.println("\n[+] Scanning: " + targetUrl);

        // Step 1: Send HTTP request and fetch page content
        RequestHandler requestHandler = new RequestHandler(targetUrl);
        String pageContent = requestHandler.fetchPageContent();

        if (pageContent == null) {
            Logger.getLogger(ScannerEngine.class.getName()).log(Level.SEVERE, "[-] Failed to retrieve page content.");
            return;
        }

        // Step 2: Check for vulnerabilities
        System.out.println("\n[🔍] Checking for security vulnerabilities...");

        // Scan for SQL Injection
        for (String sqli : VulnerabilityDetector.scanSQLInjection(pageContent)) {
            System.out.println(sqli);
        }

        // Scan for XSS
        for (String xss : VulnerabilityDetector.scanXSS(pageContent)) {
            System.out.println(xss);
        }

        System.out.println("\n[✔] Scan completed.");
    }
}

