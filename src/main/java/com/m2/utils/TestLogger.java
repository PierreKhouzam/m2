package com.m2.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLogger {

    // Initialize Log4j logs
    private static final Logger log = LogManager.getLogger(TestLogger.class.getName());


    // This is to print log at the start of test suite
    public static void startTestSuite(String testSuiteName) {
        log.info("*** Test Suite " + testSuiteName + " started ***");
    }

    // Other useful methods
    public static void info(String message) {
        log.info(message);
    }

    public static void warn(String message) {
        log.warn(message);
    }

    public static void error(String message) {
        log.error(message);
    }

    public static void fatal(String message) {
        log.fatal(message);
    }

    public static void debug(String message) {
        log.debug(message);
    }
}


