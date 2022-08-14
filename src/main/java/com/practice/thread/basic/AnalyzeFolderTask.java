package com.practice.thread.basic;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class AnalyzeFolderTask implements Runnable{

    private BlockingQueue<String> analyzeFoldersQueue = new ArrayBlockingQueue<String>(100);
    private FileCopyingTask fileCopyingTask;

    public AnalyzeFolderTask(String filePath,FileCopyingTask fileCopyingTask) {
        this.analyzeFoldersQueue.add(filePath);
        this.fileCopyingTask = fileCopyingTask;
    }

    public BlockingQueue<String> getAnalyzeFoldersQueue() {
        return analyzeFoldersQueue;
    }

    @Override
    public void run() {
        if(this.analyzeFoldersQueue.size()>0) {
            System.out.println("Inside AnalyzeFolderTask :: run method");
            String folderName = analyzeFoldersQueue.poll();
            File folder = new File(folderName);
            File[] filesList = folder.listFiles();
            for (File file : filesList) {
                if (file.isDirectory()) {
                    this.analyzeFoldersQueue.add(file.getAbsolutePath());
                    fileCopyingTask.getFilesToCopyQueue().add(file.getAbsolutePath());
                }
            }
        }
    }
}
