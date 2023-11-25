package org.example;

import java.util.Map;
import java.util.stream.Collectors;

public class PrettyConverter implements Converter {
    @Override
    public String convert(Map<String, Differ.DiffData> diffMap) {
        String result = diffMap.keySet().stream().map(key -> {
            Differ.DiffData va = diffMap.get(key);
            String prefix = getPrefix(va.changeType());
            Object val = va.value();

            if (va.changeType().equals(ChangeTypes.CHANGED)) {
                return String.format("+ %s: %s\n- %s: %s\n", key, val, key, va.prevValue());
            } else {
                return String.format("%s%s: %s\n", prefix, key, val);
            }


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
            case UNCHANGED -> {
                return "  ";
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
