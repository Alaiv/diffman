package org.example;

import java.security.InvalidParameterException;
import java.util.List;

public class ConverterSelector {
    public static Converter selectConverter(String type) {
        List<Converter> converterList = List.of(new PrettyConverter(), new PlainConverter(), new JsonConverter());
        for (var converter : converterList) {
            if (converter.getType().equals(type))
                return converter;
        }

       throw new InvalidParameterException("Передан несуществующий тип");
    }
}
