import com.fasterxml.jackson.databind.ObjectMapper;
import model.Payload;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static junit.framework.Assert.assertEquals;

/**
 * Created by valluri.chowdary on 04/11/15.
 */
public class PayloadTest {

    @Test
    public void test() throws IOException {

        byte[] jsonData = Files.readAllBytes(Paths.get("testPayload.json"));

        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        //convert json string to object
        Payload payload = objectMapper.readValue(jsonData, Payload.class);

        assertEquals(payload.getCountry(), "Germany");

        assertEquals(payload.getInEurope(), "true");

    }
}
