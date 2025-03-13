package org.example;

import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

/**
 * Integrates OWASP ZAP for automated vulnerability scanning.
 */
public class ZapScanner {
    private static final String ZAP_ADDRESS = "localhost";
    private static final int ZAP_PORT = 8080;
    private static final String API_KEY = "";  // If you set an API key in ZAP, enter it here.

    private final ClientApi zap;

    public ZapScanner() {
        this.zap = new ClientApi(ZAP_ADDRESS, ZAP_PORT, API_KEY);
    }

    /**
     * Starts OWASP ZAP Passive Scan.
     * @param targetUrl The URL to scan.
     */
    public void passiveScan(String targetUrl) {
        try {
            System.out.println("[+] Starting Passive Scan on: " + targetUrl);
            zap.spider.scan(targetUrl, null, null, null, null);
            System.out.println("[✔] Passive Scan Completed.");
        } catch (ClientApiException e) {
            System.out.println("❌ Error in Passive Scan: " + e.getMessage());
        }
    }

    /**
     * Starts OWASP ZAP Active Scan.
     * @param targetUrl The URL to scan.
     */
    public void activeScan(String targetUrl) {
        try {
            System.out.println("[+] Starting Active Scan on: " + targetUrl);
            zap.ascan.scan(targetUrl, "True", "False", null, null, null);
            System.out.println("[✔] Active Scan Completed.");
        } catch (ClientApiException e) {
            System.out.println("❌ Error in Active Scan: " + e.getMessage());
        }
    }

    /**
     * Fetches security alerts from OWASP ZAP.
     * @return A report containing detected vulnerabilities.
     */
    public String getAlerts() {
        try {
            return new String(zap.core.alerts(null, null, null));
        } catch (ClientApiException e) {
            return "❌ Error fetching alerts: " + e.getMessage();
        }
    }
}
