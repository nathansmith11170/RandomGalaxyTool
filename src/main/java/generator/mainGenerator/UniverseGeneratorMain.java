package generator.mainGenerator;

import model.Galaxy;

import freemarker.template.TemplateException;
import generator.UniverseGenerator;

import java.io.IOException;
import java.net.URISyntaxException;

public class UniverseGeneratorMain {

    public void GenerateUniverse(Galaxy gal) throws IOException, TemplateException, URISyntaxException {

        UniverseGenerator universeGenerator = new UniverseGenerator(gal);
        universeGenerator.generateUniverse(gal);

        // if(args.length > 1 && args[1].equalsIgnoreCase("generateSchema")){
        //     generateSchema(objectMapper);
        // }
    }

    // private static void generateSchema(ObjectMapper mapper) throws JsonProcessingException {
    //     SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
    //     mapper.acceptJsonFormatVisitor(Galaxy.class, visitor);
    //     JsonSchema schema = visitor.finalSchema();
    //     System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema));
    // }
}
