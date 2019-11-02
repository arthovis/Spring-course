package com.sda.spring.core.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DemoResourceLoader {
    @Autowired
    private static ResourceLoader resourceLoader;

    public static void main(String[] args) throws IOException {


        ApplicationContext context = new AnnotationConfigApplicationContext();

        Resource resourceFromContext = context.getResource("file:D:\\proiecte java\\spring-course\\spring-core\\src\\main\\resources\\data.txt");
        System.out.println(resourceFromContext);

        printResourceContent(resourceFromContext);

        Resource resourceFromClassPath = context.getResource("classpath:classPathData.txt");
        printResourceContent(resourceFromClassPath);
    }
    private static void printResourceContent(Resource resource) throws IOException {
        InputStream in = resource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        while (true){
            String line = reader.readLine();
            if (line==null){
                break;
            }
            System.out.println(line);
        }
        reader.close();
    }
}
