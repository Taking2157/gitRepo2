package com.taking.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestHashMap {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("id", "1705110143");
        map.put("name", "Taking");
        map.put("age", "22");
        Set<String> set = map.keySet();
        for (String s :
                set) {
            System.out.println(map.get(s));
        }
    }
}
