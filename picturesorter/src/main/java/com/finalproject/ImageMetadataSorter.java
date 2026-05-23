package com.finalproject;

import java.io.File;

public class ImageMetadataSorter{
    public static void processFiles(File[] files){
        for (File file : files) {
            if (file.isFile()) {
                // Process the file
                System.out.println("Processing file: " + file.getName());
            } else if (file.isDirectory()) {
                // Recursively process the directory
                processFiles(file.listFiles());
            }
        }
    }
    public static void folderProcessor(String inputDirectory, String outputDirectory){
        // Storing the name of files and directories
        // in an array of File type
        File[] files = new File(inputDirectory).listFiles();

        // Calling method 1 to
        // display files
        processFiles(files);
    }
}

