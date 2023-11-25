package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.*;
import java.util.stream.Collectors;

public class Differ {
    public String getDiff(String firstItem, String secondItem, String converterType) throws JsonProcessingException {
        if (firstItem.isEmpty() || secondItem.isEmpty()) throw new IllegalArgumentException("Переданы пустые файлы");
        if (!checkFormatter(converterType)) converterType = "pretty";

        Parser parser = new Parser();
        Map<String, Object> firstItemMap = parser.parse(firstItem);
        Map<String, Object> secondItemMap = parser.parse(secondItem);

        Map<String, DiffData> res = buildDiffMap(firstItemMap, secondItemMap);

        Converter converter = ConverterSelector.selectConverter(converterType);
        return converter.convert(res);
    }

    private Map<String, DiffData> buildDiffMap(Map<String, Object> firstItemMap, Map<String, Object> secondItemMap
    ) {

        Map<String, DiffData> res = secondItemMap
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> {
                        ChangeTypes changeType = getType(firstItemMap, secondItemMap, e.getKey());
                            if (changeType.equals(ChangeTypes.CHANGED)) {
                                return new DiffData(e.getValue(), changeType, firstItemMap.get(e.getKey()));
                            } else {
                                return new DiffData(e.getValue(), changeType, null);
                            }
                        }
                ));

        firstItemMap.forEach((key, val) ->
                res.computeIfAbsent(key, x -> new DiffData(val, ChangeTypes.REMOVED, null)));

        return res;
    }

    private boolean checkFormatter(String formatter) {
        return formatter.equals("pretty") || formatter.equals("plain") || formatter.equals("json");
    }


    private ChangeTypes getType(Map<String, Object> f, Map<String, Object> s, String key) {
        ChangeTypes type;

        if (f.containsKey(key)) {
            type = Objects.equals(s.get(key), f.get(key)) ? ChangeTypes.UNCHANGED : ChangeTypes.CHANGED;
        } else {
            type = ChangeTypes.ADDED;
        }

        return type;
    }

    public record DiffData(Object value, ChangeTypes changeType, Object prevValue) {
        @Override
        public String toString() {
            return this.value + " " + changeType;
        }
    }
}

