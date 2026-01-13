package config;

import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class ResolveProperties {
	
    private static final Properties PROPS = new Properties();

    public static Optional<String> getPropertieValue(String key) {
        try (InputStream is = ResolveProperties.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (is != null) PROPS.load(is);
            return Optional.ofNullable(System.getenv(key))
                    .or(() -> Optional.ofNullable(PROPS.getProperty(key)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
