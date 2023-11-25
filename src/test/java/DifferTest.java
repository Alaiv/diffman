import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.Differ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DifferTest {
    @Test
    public void changedTest() throws JsonProcessingException {
        String json1 = "{\"name\": \"lala\", \"age\": 12}";
        String json2 = "{\"name\": \"lala\", \"age\": 13}";
        String diff = "{\n  name: lala\n+ age: 13\n- age: 12\n}";
        Differ differ = new Differ();
        String dif = differ.getDiff(json1, json2, "pretty");

        Assertions.assertEquals(diff, dif);
    }

    @Test
    public void addTest() throws JsonProcessingException {
        String json1 = "{\"name\": \"lala\", \"age\": 12}";
        String json2 = "{\"name\": \"lala\", \"age\": 13, \"stuff\": 3213}";
        String diff = "{\n  name: lala\n+ age: 13\n- age: 12\n+ stuff: 3213\n}";
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

    @Test
    public void objValueTest() throws JsonProcessingException {
        String json1 = "{\"name\": \"lala\", \"age\": 12, \"data\": {\"st\": 123}}";
        String json2 = "{\"name\": \"lala\", \"data\": {\"st\": 1234}}";
        String diff = "{\n+ data: {st=1234}\n- data: {st=123}\n  name: lala\n- age: 12\n}";
        Differ differ = new Differ();
        String dif = differ.getDiff(json1, json2, "pretty");

        Assertions.assertEquals(diff, dif);
    }

    @Test
    public void arrValueTest() throws JsonProcessingException {
        String json1 = "{\"name\": \"lala\", \"age\": 12, \"data\": [1, 2, 3]}";
        String json2 = "{\"name\": \"lala\", \"data\": [3, 4, 5]}";
        String diff = "{\n+ data: [3, 4, 5]\n- data: [1, 2, 3]\n  name: lala\n- age: 12\n}";
        Differ differ = new Differ();
        String dif = differ.getDiff(json1, json2, "pretty");

        Assertions.assertEquals(diff, dif);
    }

    @Test
    public void arrObjectValueTest() throws JsonProcessingException {
        String json1 = "{\"name\": \"lala\", \"age\": 12, \"data\": {\"inner\": [1, 2, 3, 4]}}";
        String json2 = "{\"name\": \"lala\", \"data\": {\"inner\": [1, 2, 3]}}";
        String diff = "{\n+ data: {inner=[1, 2, 3]}\n- data: {inner=[1, 2, 3, 4]}\n  name: lala\n- age: 12\n}";
        Differ differ = new Differ();
        String dif = differ.getDiff(json1, json2, "pretty");

        Assertions.assertEquals(diff, dif);
    }
}
