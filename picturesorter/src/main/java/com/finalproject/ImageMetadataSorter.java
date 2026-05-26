package com.finalproject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class ImageMetadataSorter{
    //iterates through the directory and lists the files, if it is a folder it goes into it and repeats the process. 
    private static String outputDirectory;
    public static void processFiles(File[] files){
        for (File file : files) {
            if (file.isFile()) {
                // Process the file
                System.out.println("Processing file: " + file.getName());
                try {
                    Metadata metadata = ImageMetadataReader.readMetadata(file);
                    System.out.println(metadata);
                    doCopy(metadata, file);
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
        ImageMetadataSorter.outputDirectory = outputDirectory;
        processFiles(files);
    }

    private static void doCopy(Metadata metadata, File file){

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
                    /* 
                    System.out.println(date.getYear()+1900); // Date.getYear() returns years since 1900
                    System.out.println(date.getMonth()+1); // Date.getMonth() returns months from 0-11
                    System.out.println(date.getDate());
                    */
                    copyFileToDestination(file, Integer.toString(date.getYear()+1900), Integer.toString(date.getMonth()+1), Integer.toString(date.getDate()));
                    return;
                }
            }
        }
    }

    public static void createDirectoryIfNotPresent(String dir){
        File directory = new File(dir);
        System.out.println("Checking directory: " + dir);
        if (!directory.exists()) {
            System.out.println("Directory does not exist. Creating directory: " + dir);
            directory.mkdirs();
        }
    }

    public static void copyFileToDestination(File source, String year, String month, String day){
        //file seperator is /
        createDirectoryIfNotPresent(ImageMetadataSorter.outputDirectory + File.separator + year);
        createDirectoryIfNotPresent(ImageMetadataSorter.outputDirectory + File.separator + year + File.separator + month);
        createDirectoryIfNotPresent(ImageMetadataSorter.outputDirectory + File.separator + year + File.separator + month + File.separator + day);
        String destinationPath = ImageMetadataSorter.outputDirectory + File.separator + year + File.separator + month + File.separator + day + File.separator + source.getName();
        try{
            Files.copy(source.toPath(), new File(destinationPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException e){
            System.out.println(e);
        }
    }
}

