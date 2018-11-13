package com.ysc.myspringboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyspringbootApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("begin:");
        HashMap<String, HashSet<String>> listPathMap = new HashMap<>();
        File listPathDir = new File("src/test/resources/list-path");

        if (listPathDir.exists()) {
            File[] listPathFiles = listPathDir.listFiles();
            if (listPathFiles != null) {
                Yaml yaml = new Yaml();
                Map map = new HashMap();
                for (File file : listPathFiles) {
                    try {
                        map = yaml.load(new FileInputStream(file));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    HashSet<String> listPathSet = new HashSet<>();
                    System.out.println(map.toString());
                    yaml2Set(listPathSet, map, "");
                    listPathMap.put(file.getName().split("\\.")[0], listPathSet);
                }
            }
        }

        for (String key : listPathMap.keySet()) {
            System.out.println(key + ":" + listPathMap.get(key).toString());
        }
    }

    public static void yaml2Set(HashSet<String> set, Map map, String string) {
        Set keySet = map.keySet();
        if (keySet.size() == 0)
            return;
        for (Object key : keySet) {
            Object value = map.get(key);
            if (value == null) {
                set.add(string + key.toString());
            } else {
                yaml2Set(set, (Map)map.get(key), string + key.toString() + "/");
            }
        }
    }

}
