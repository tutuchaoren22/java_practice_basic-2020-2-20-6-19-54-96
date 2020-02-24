package com.thoughtworks.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {
    public static void copyDirectory(File from, File to) throws IOException {
        if (!to.exists()) {
            System.out.println("新建文件：" + to.mkdirs());
        } else {
            deleteAllFile(to);
        }
        File[] fromList = from.listFiles();
        if (fromList != null) {
            for (File file : fromList) {
                File fromFile = new File(from.getPath() + File.separator + file.getName());
                File toFile = new File(to.getPath() + File.separator + file.getName());
                if (file.isDirectory()) {
                    copyDirectory(fromFile, toFile);
                } else {
                    copyFile(fromFile, toFile);
                }
            }
        }
    }

    public static void deleteAllFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            assert files != null;
            for (File f : files) {
                deleteAllFile(f);
            }
        } else {
            System.out.println("删除文件：" + file.delete());
        }
    }

    public static void copyFile(File from, File to) throws IOException {
        try (InputStream input = new FileInputStream(from);
             OutputStream output = new FileOutputStream(to)) {
            int n;
            while ((n = input.read()) != -1) {
                output.write(n);
            }
        }
    }
}