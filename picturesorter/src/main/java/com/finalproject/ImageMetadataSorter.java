package com.finalproject;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class ImageMetadataSorter{
    //iterates through the directory and lists the files, if it is a folder it goes into it and repeats the process. 
    public static void processFiles(File[] files){
        for (File file : files) {
            if (file.isFile()) {
                // Process the file
                System.out.println("Processing file: " + file.getName());
                try {
                    Metadata metadata = ImageMetadataReader.readMetadata(file);
                    System.out.println(metadata);
                    print(metadata);
                } catch (ImageProcessingException e) {
                    System.out.println(e);
                } catch (IOException e) {
                    System.out.println(e);
                }
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
        processFiles(files);
    }
    private static void print(Metadata metadata){

        //
        // A Metadata object contains multiple Directory objects
        //
        for (Directory directory : metadata.getDirectories()) {

            //
            // Each Directory stores values in Tag objects
            //
            for (Tag tag : directory.getTags()) {
                if(tag.getTagName().contains("Date")){
                    System.out.println(tag.getDescription());
                    Date date = directory.getDate(tag.getTagType());
                    System.out.println(date.getYear()+1900); // Date.getYear() returns years since 1900
                    System.out.println(date.getMonth()+1); // Date.getMonth() returns months from 0-11
                    System.out.println(date.getDate());
                }
            }
        }
    }
}

