package org.example;

import java.util.Map;

public class PlainConverter implements Converter{
    public String convert(Map<String, Differ.DiffData> diffMap) {
        return null;
    }

    @Override
    public String getType() {
        return "plain";
    }
}
