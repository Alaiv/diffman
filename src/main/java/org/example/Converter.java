package org.example;

import java.util.Map;

public interface Converter {
    String convert(Map<String, Differ.Change> diffMap);

    String getType();
}
