package com.ysc.myspringboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.Yaml;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.jar.JarFile;

@RestController
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    private static HashMap<String, HashSet<String>> emotorXmlArrayTagMap = new HashMap<>();
    private static final String EMOTOR_XML_ARRAY_TAG_DIR = "src/main/resources/xml-array-tag/emotor";

    static {
        File emotorXmlArrayTagDir = new File(EMOTOR_XML_ARRAY_TAG_DIR);
        if (emotorXmlArrayTagDir.exists()) {
            File[] files = emotorXmlArrayTagDir.listFiles();
            if (files != null) {
                Yaml yaml = new Yaml();
                Map map = new HashMap();
                for (File file : files) {
                    String[] nameBlock = file.getName().split("\\.");
                    if (nameBlock.length != 2
                            || (!nameBlock[1].equalsIgnoreCase("yml")
                            && !nameBlock[1].equalsIgnoreCase("yaml"))) {
                        continue;
                    }
                    try {
                        map = yaml.load(new FileInputStream(file));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (map == null) {
                        continue;
                    }
                    HashSet<String> xmlArrayTagSet = new HashSet<>();
                    yaml2Set(xmlArrayTagSet, map, "");
                    emotorXmlArrayTagMap.put(nameBlock[0], xmlArrayTagSet);
                }
            }
        }
        for (String key : emotorXmlArrayTagMap.keySet()) {
            System.out.println(key);
            Set<String> set = emotorXmlArrayTagMap.get(key);
            for (String s : set)
                System.out.println(s);
        }
    }

    public static void main(String[] args) {
        System.out.println("end");
    }

    private static void yaml2Set(HashSet<String> set, Map map, String string) {
        Set keySet = map.keySet();
        if (keySet.size() == 0) {
            return;
        }
        for (Object key : keySet) {
            Object value = map.get(key);
            if (value == null) {
                set.add(string + "/" +  key.toString());
            } else {
                yaml2Set(set, (Map)value, string + "/" +  key.toString());
            }
        }
    }

    @RequestMapping(value = {"hi"}, method = RequestMethod.GET)
    public String say(@RequestParam(value = "name") String name,
                      HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("hello, ").append(name).append("<br>");
        stringBuilder.append("getServletPath():").append(request.getServletPath()).append("<br>");
        stringBuilder.append("getContextPath():").append(request.getContextPath()).append("<br>");
        stringBuilder.append("getRequestURI():").append(request.getRequestURI()).append("<br>");
        stringBuilder.append("getRequestURL():").append(request.getRequestURL()).append("<br>");
        return stringBuilder.toString();
    }
    @RequestMapping(value = {"/test"})
    public String test() {
        StringBuilder sb = new StringBuilder();
        for (String key : emotorXmlArrayTagMap.keySet()) {
            sb.append(key).append("=").append(emotorXmlArrayTagMap.get(key).toString()).append("<br>");
        }
        return sb.toString();
    }
}
