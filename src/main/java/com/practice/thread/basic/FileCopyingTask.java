package com.practice.thread.basic;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FileCopyingTask implements Runnable {

    private String destFileLocation;
    private String sourceFileLocation;

    public BlockingQueue<String> filesToCopyQueue = new ArrayBlockingQueue<String>(100);

    public FileCopyingTask(String sourceFileLocation,String destFileLocation) {
        this.destFileLocation = destFileLocation;
        this.sourceFileLocation = sourceFileLocation;
    }

    public BlockingQueue<String> getFilesToCopyQueue() {
        return filesToCopyQueue;
    }

    public void run(){
        if(this.filesToCopyQueue.size()>0) {
            System.out.println("Inside FileCopyingTask :: run method");
            String srcFilePolled = filesToCopyQueue.poll();
            File srcFile = new File(srcFilePolled);
            String srcFileRelativePath = srcFile.getAbsolutePath();
            srcFileRelativePath = srcFileRelativePath.substring(sourceFileLocation.length(), srcFileRelativePath.length());
            String destFileFullPath = destFileLocation + srcFileRelativePath;
            File destFileObj = new File(destFileFullPath);
            if (!destFileObj.isDirectory()) {
                new File(destFileFullPath).mkdirs();
            }
            try {
                System.out.println("Initiated file copy between srcFile:" + srcFile + ",destFile:" + destFileFullPath);
                Files.copy(srcFile.toPath(), Paths.get(destFileFullPath), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
