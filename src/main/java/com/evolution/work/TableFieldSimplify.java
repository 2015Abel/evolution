package com.evolution.work;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName TableFieldSimplify
 * @Description: 表字段简化
 * @Author: Lzj
 * @Date: 2019/4/1 11:02
 */
public class TableFieldSimplify {
    final String path = "f:/temp.txt";

    public void handle() throws IOException {
        File file = FileUtils.getFile(path);
        if(!file.exists()){
            throw new RuntimeException("file not exists!");
        }

        List<String> contentList = FileUtils.readLines(file,"UTF-8");
        Pattern pattern = Pattern.compile("([^\\)]*\\))");
        final String DATE = " date ";
        final String DATETIME = " datetime ";
        final String TIMESTAMP = " timestamp ";
        for(String content:contentList){
            Matcher matcher = pattern.matcher(content);
            if(matcher.find()){
                System.out.println(matcher.group());
            }else if(content.contains(DATETIME)){
                System.out.println(content.substring(0,content.indexOf(DATETIME)+DATETIME.length()));
            }else if(content.contains(DATE)){
                System.out.println(content.substring(0,content.indexOf(DATE)+DATE.length()));
            }else if(content.contains(TIMESTAMP)){
                System.out.println(content.substring(0,content.indexOf(TIMESTAMP)+TIMESTAMP.length()));
            }else {
                System.out.println(content);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        TableFieldSimplify bean = new TableFieldSimplify();
        bean.handle();
    }


}
