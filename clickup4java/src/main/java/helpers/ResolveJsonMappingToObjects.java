package helpers;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ResolveJsonMappingToObjects {

	private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> T convertJSON(String json, Class<T> targetType) {
        try {
            return MAPPER.readValue(json, targetType);
        } catch (Exception e) {
            throw new RuntimeException("Error while converting JSON", e);
        }
    }
}

