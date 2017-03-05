import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class SchemaTest {


    @Test
    public void advanced() throws IOException, URISyntaxException {
        URL xsd = this.getClass().getClassLoader().getResource("advanced1.xsd");

        //AvroCompiler.compileSchemas(new File[]{new File(xsd.toURI())}, new File("./"));

        System.out.println("DONE");
    }
}
