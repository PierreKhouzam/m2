package com.m2.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentManager {
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports report = ExtentReporter.getReport();

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized void startTest(String testName) {
        ExtentTest test = report.createTest(testName);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
    }
}