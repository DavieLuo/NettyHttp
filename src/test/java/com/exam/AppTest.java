package com.exam;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testpath() {
        try {
            String basepack = "com.exam.controllers";
            String basepack2 = basepack.replace(".", File.separator);
            String classPath = AppTest.class.getResource("/").getPath();
            String path = classPath + basepack2;
            System.out.println(classPath + "--" + path);

            getControllerName(new File(path));
            classNames.forEach(it -> {
                System.out.println(basepack+"."+it.substring(0, it.length()-6));
                try {
                    Class mc =Class.forName(basepack+"."+it.substring(0, it.length()-6));
                    System.out.println(mc);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } );

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    List<String> classNames = new ArrayList();

    public void getControllerName(File file) throws IOException {
       
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File tempfile : files) {
                getControllerName(tempfile);
            }
        }else{
            if(file.getName().endsWith(".class")){
                classNames.add(file.getName());
            }

        }
        
    }
}
