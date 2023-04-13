package com.libyao.generics_started;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class DemoStarted {
    private static final Logger logger = LogManager.getLogger(DemoStarted.class);

    public static void main(String[] args) {
        List arrayList = new ArrayList();
        // List<String> arrayList = new ArrayList<String>();
        arrayList.add("aaaa");
        arrayList.add(100);

        for (int i = 0; i < arrayList.size(); i++) {
            String item = (String) arrayList.get(i);
            logger.info("泛型测试: item = " + item);
        }
    }
}
