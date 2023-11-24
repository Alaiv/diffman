package org.example;

import java.util.Map;

public class PlainConverter implements Converter{
    @Override
    public String convert(Map<String, Differ.Change> diffMap) {
        return null;
    }

    @Override
    public String getType() {
        return "plain";
    }
}
