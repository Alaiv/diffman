import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ParserTest {
    @Test
    public void basicTest() throws JsonProcessingException {
        Parser parser = new Parser();
        String json1 = "{\"name\": \"lala\", \"age\": 12}";
        Map<String, Object> firstItemMap = parser.parse(json1);
        Assertions.assertTrue(firstItemMap.containsKey("name"));
        Assertions.assertTrue(firstItemMap.containsValue("lala"));
        Assertions.assertTrue(firstItemMap.containsKey("age"));
        Assertions.assertTrue(firstItemMap.containsValue(12));
    }
}
