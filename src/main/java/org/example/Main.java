package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== Java Web Security Scanner =====");
        Scanner scanner = new Scanner(System.in);

        // Get the target URL from the user
        System.out.print("Enter the target URL: ");
        String targetUrl = scanner.nextLine();

        // Start the scan
        ScannerEngine securityScanner = new ScannerEngine(targetUrl);
        securityScanner.startScan();

        scanner.close();
    }
}