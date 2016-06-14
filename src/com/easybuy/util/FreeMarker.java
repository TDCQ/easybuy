package com.easybuy.util;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;

/**
 * Created by lenovo on 2016/6/14.
 */
public class FreeMarker {
    private static Configuration configuration;
    private FreeMarker(){
        configuration = new Configuration(Configuration.VERSION_2_3_24);
        try {
            configuration.setDirectoryForTemplateLoading(new File("E:\\Develop\\IDLE\\easybuy\\web\\html"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    public static Configuration getConfiguration(){
        if(configuration==null){
            new FreeMarker();
        }
        return configuration;
    }
}
