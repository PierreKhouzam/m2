package com.m2.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
    static ExtentReports report;

    public synchronized static ExtentReports getReport() {
        if (report == null) {
            // Set HTML report file location
            ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")
                    + "/extentreports/ExtentReportResults.html");
            report = new ExtentReports();
            report.attachReporter(spark);
        }
        return report;
    }
}

