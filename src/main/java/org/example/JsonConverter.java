package org.example;

import java.util.Map;

public class JsonConverter implements Converter{
    @Override
    public String convert(Map<String, Differ.Change> diffMap) {
        return null;
    }

    @Override
    public String getType() {
        return "json";
    }
}
