package com.bjtu.redis.filelistener;

import java.io.File;

import com.bjtu.redis.JsonRead;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class FileListener extends FileAlterationListenerAdaptor {

    private JsonRead jsonRead;
    FileMonitor monitor = null;

    @Override
    public void onStart(FileAlterationObserver observer) {
        //System.out.println("onStart");
    }

    @Override
    public void onDirectoryCreate(File directory) {
        System.out.println("onDirectoryCreate:" + directory.getName());
    }

    @Override
    public void onDirectoryChange(File directory) {
        System.out.println("onDirectoryChange:" + directory.getName());
    }

    @Override
    public void onDirectoryDelete(File directory) {
        System.out.println("onDirectoryDelete:" + directory.getName());
    }

    @Override
    public void onFileCreate(File file) {
        System.out.println("onFileCreate:" + file.getName());
    }

    @Override
    public void onFileChange(File file) {
        System.out.println("检测到文件更改 " + file.getName());
        System.out.println("重新读取文件 " + file.getName());
        jsonRead=JsonRead.getJsonRead();
        jsonRead.setJsonRead();
    }

    @Override
    public void onFileDelete(File file) {
        System.out.println("onFileDelete :" + file.getName());
    }

    @Override
    public void onStop(FileAlterationObserver observer) {
        //System.out.println("onStop");
    }


}