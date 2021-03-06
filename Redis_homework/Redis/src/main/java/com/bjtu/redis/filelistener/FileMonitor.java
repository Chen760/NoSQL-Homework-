package com.bjtu.redis.filelistener;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class FileMonitor {

    FileAlterationMonitor monitor = null;

    public FileMonitor(long interval) throws Exception {
        monitor = new FileAlterationMonitor(interval);
    }

    /**
     * 给文件添加监听
     * @param path
     * @param listener
     */
    public void monitor(String path, FileAlterationListener listener) {
        FileAlterationObserver observer = new FileAlterationObserver(new File(path));
        monitor.addObserver(observer);
        observer.addListener(listener);
    }

    public void stop() throws Exception {
        monitor.stop();
    }

    public void start() throws Exception {
        monitor.start();
    }

    public static void filemonitor() throws Exception {
        //初始化监听
        FileMonitor m = new FileMonitor(1000);//设置监控的间隔时间
        //指定文件夹，添加监听
        m.monitor("D:\\IdeaProjects\\Redis\\src\\main\\resources", new FileListener());
        //开启监听
        m.start();
    }
}