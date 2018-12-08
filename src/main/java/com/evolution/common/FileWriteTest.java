package com.evolution.common;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @description: TODO 类描述
 * @author: liuzijian
 * @date: 2018-12-08 15:09
 */
public class FileWriteTest {
    public static void main(String[] args) throws IOException {
        File file = new File("F:/file_write_test.log");
        if(!file.exists()){
            file.createNewFile();
        }

        RandomAccessFile raf = new RandomAccessFile(file,"rw");
        while (true){
            raf.write("dfjsdfeweqhrofsdfjaioaf".getBytes("utf-8"));
        }
    }
}
