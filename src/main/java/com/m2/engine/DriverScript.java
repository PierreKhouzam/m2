package com.m2.engine;

import java.io.FileInputStream;
import java.util.Properties;

public class DriverScript {
    static Properties objectRepo;
    static String objectRepoPath;

    public static void objectRepoLoad() {
        try {
            objectRepoPath = System.getProperty("user.dir") + "/config/ObjectRepo.txt";
            FileInputStream fs = new FileInputStream(objectRepoPath);
            objectRepo = new Properties(System.getProperties());
            objectRepo.load(fs);

        } catch (Exception e) {
            System.out.println("Couldn't load object Repo " + e.getMessage());
        }
    }
}

