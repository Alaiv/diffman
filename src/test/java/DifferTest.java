import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.Differ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DifferTest {
    @Test
    public void changedTest() throws JsonProcessingException {
        String json1 = "{\"name\": \"lala\", \"age\": 12}";
        String json2 = "{\"name\": \"lala\", \"age\": 13}";
        String diff = "{\n  name: lala\n> age: 13\n}";
        Differ differ = new Differ();
        String dif = differ.getDiff(json1, json2, "pretty");

        Assertions.assertEquals(diff, dif);
    }

    @Test
    public void addTest() throws JsonProcessingException {
        String json1 = "{\"name\": \"lala\", \"age\": 12}";
        String json2 = "{\"name\": \"lala\", \"age\": 13, \"stuff\": 3213}";
        String diff = "{\n  name: lala\n> age: 13\n+ stuff: 3213\n}";
        Differ differ = new Differ();
        String dif = differ.getDiff(json1, json2, "pretty");

        Assertions.assertEquals(diff, dif);
    }

    @Test
    public void removeTest() throws JsonProcessingException {
        String json1 = "{\"name\": \"lala\", \"age\": 12}";
        String json2 = "{\"name\": \"lala\"}";
        String diff = "{\n  name: lala\n- age: 12\n}";
        Differ differ = new Differ();
        String dif = differ.getDiff(json1, json2, "pretty");

        Assertions.assertEquals(diff, dif);
    }
}
