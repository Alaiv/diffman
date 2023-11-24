import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

public class ConverterSelectorTest {
    @Test
    public void prettyTest() {
        Converter converter = ConverterSelector.selectConverter("pretty");
        Assertions.assertTrue(converter instanceof PrettyConverter);
    }

    @Test
    public void plainTest() {
        Converter converter = ConverterSelector.selectConverter("plain");
        Assertions.assertTrue(converter instanceof PlainConverter);
    }

    @Test
    public void jsonTest() {
        Converter converter = ConverterSelector.selectConverter("json");
        Assertions.assertTrue(converter instanceof JsonConverter);
    }

    @Test
    public void invalidTest() {
        InvalidParameterException thrown = Assertions.assertThrows(
                InvalidParameterException.class,
                () -> ConverterSelector.selectConverter("dasdas")
                , "dsadas"
        );

        Assertions.assertEquals("Передан несуществующий тип", thrown.getMessage());
    }
}
