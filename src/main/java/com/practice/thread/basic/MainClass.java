package com.practice.thread.basic;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass {

    /*
    public static void createThreadClassStart(){
        ThreadClass t = new ThreadClass();
        t.start();
    }

    public static void createRunnableClass(){
        Thread t = new Thread(new ThreadRunnable());
        t.start();
    }

    public static void createExecutableClass(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(new ThreadRunnable());
    }

    */

  /*  private static void copyFile(String srcFileLocation,String destFileLocation) throws IOException {
        File f = new File(srcFileLocation);
        File[] listFiles = f.listFiles();
        for(File file:listFiles) {
            if (file.isDirectory()) {
                findFolder(srcFileLocation.length(),destFileLocation,file);
            }else if(file.isFile()){
                String relativePath = file.getAbsolutePath().replaceAll(srcFileLocation,"");
                String fullPath = destFileLocation+relativePath;
                findFiles(fullPath , file);
            }
        }
    }

    private static void findFolder(int srcFileLocationLength,String destFileLocation,File folder) throws IOException {
        File[] filesList = folder.listFiles();
        for(File file: filesList) {
            if(file.isDirectory()){
                findFolder(srcFileLocationLength,destFileLocation,file);
            }else{
                System.out.println("file.getAbsolutePath():"+file.getAbsolutePath()+",srcFileLocation:"+srcFileLocationLength);
                String destFilePath = file.getAbsolutePath();
                destFilePath = destFilePath.substring(srcFileLocationLength,file.getAbsolutePath().length());
                findFiles(destFileLocation+destFilePath,file);
            }
        }
    }


    private static void findFiles(String destFullPath, File file){
        try {
            File destFile = new File(destFullPath);
            if(!destFile.isDirectory()){
                new File(destFullPath).mkdirs();
            }
            Files.copy(file.getAbsoluteFile().toPath(), Paths.get(destFullPath), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("SourceFileName::" + file.getAbsoluteFile() + ",destFileName::" + destFullPath);
        }catch (IOException e){
            e.printStackTrace();
        }
    } */

    public static void main(String[] args) {
        System.out.println("Inside MainClass started");
        //createThreadClassStart();
        //screateRunnableClass();
        //createExecutableClass();
        try {
            //copyFile("H:\\File_Sent_SourceLocation\\Groovy","H:\\File_copied_DestLocation");
            String srcFileLocation = "H:\\File_Sent_SourceLocation\\Groovy";
            String destFileLocation = "H:\\File_copied_DestLocation";
            FileCopyingTask fileCopyingTask = new FileCopyingTask(srcFileLocation,destFileLocation);
            AnalyzeFolderTask analyzeFolderTask = new AnalyzeFolderTask(srcFileLocation,fileCopyingTask);

            ExecutorService executorService = Executors.newFixedThreadPool(10);
            executorService.execute(fileCopyingTask);
            executorService.execute(analyzeFolderTask);

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Inside MainClass ended");
    }
}
