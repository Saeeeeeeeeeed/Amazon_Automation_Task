package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesReader {

    private Path getFilePath(String filePath) {
        return Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "configurations", filePath);
    }

    public Properties loadPropertiesFromFile(String fileName) {
        Properties properties = new Properties();
        Path filePath = getFilePath(fileName);

        if (!Files.exists(filePath)) {
            System.err.println("Error: File '" + fileName + "' does not exist.");
            return properties; // return empty properties
        }

        try (InputStream input = Files.newInputStream(filePath)) {
            // Load properties from the input stream
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Error loading properties from file: " + e.getMessage());
        }
        return properties;
    }

}
