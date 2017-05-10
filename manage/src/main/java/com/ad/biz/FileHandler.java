package com.ad.biz;

import java.io.*;
import java.util.UUID;

/**
 * Created by xiang on 2017/5/9.
 */
public class FileHandler {

    //temp test
    public static String uploadFile1(InputStream stream, String name, String path) {
        File file = new File(path, name);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] data = new byte[1024];
            while (stream.read(data) != -1) {
                fileOutputStream.write(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "error|" + e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "error|" + e.getMessage();
        }
        return "http://localhost:8080/static/file/" + name;
    }
}
