package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;
import java.util.stream.Collectors;

public class Differ {
    public String getDiff(String firstItem, String secondItem, String converterType) throws JsonProcessingException {
        Parser parser = new Parser();
        Map<String, Object> firstItemMap = parser.parse(firstItem);
        Map<String, Object> secondItemMap = parser.parse(secondItem);

        Map<String, Change> res = secondItemMap
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> new Change(e.getValue(),
                        getType(firstItemMap, secondItemMap, e.getKey()))
                ));

        firstItemMap.forEach((key, val) -> res.computeIfAbsent(key, x -> new Change(val, ChangeTypes.REMOVED)));

        Converter converter = ConverterSelector.selectConverter(converterType);
        return converter.convert(res);
    }


    private ChangeTypes getType(Map<String, Object> f, Map<String, Object> s, String key) {
        ChangeTypes type;

        if (f.containsKey(key)) {
            type = s.get(key).equals(f.get(key)) ? ChangeTypes.NOTHING : ChangeTypes.CHANGED;
        } else {
            type = ChangeTypes.ADDED;
        }

        return type;
    }

    public static class Change {
        private final Object value;
        private final ChangeTypes changeType;

        public Change(Object value, ChangeTypes changeType) {
            this.value = value;
            this.changeType = changeType;
        }

        public Object getValue() {
            return value;
        }

        public ChangeTypes getChangeType() {
            return changeType;
        }

        @Override
        public String toString() {
            return this.value + " " + changeType;
        }
    }
}

