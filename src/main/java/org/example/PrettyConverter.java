package org.example;

import java.util.Map;
import java.util.stream.Collectors;

public class PrettyConverter implements Converter {
    @Override
    public String convert(Map<String, Differ.Change> diffMap) {
        String result = diffMap.keySet().stream().map(key -> {
            String prefix = getPrefix(diffMap.get(key).getChangeType());
            return String.format("%s%s: %s\n", prefix, key, diffMap.get(key).getValue());
        }).collect(Collectors.joining());

        return "{\n" + result + "}";
    }

    @Override
    public String getType() {
        return "pretty";
    }

    private String getPrefix(ChangeTypes type) {
        switch (type) {
            case ADDED -> {
                return "+ ";
            }
            case NOTHING -> {
                return "  ";
            }
            case CHANGED -> {
                return "> ";
            }
            case REMOVED -> {
                return "- ";
            }
            default -> {
                return "";
            }
        }
    }
}
