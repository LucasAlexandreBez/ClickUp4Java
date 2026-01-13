package helpers;

import java.util.UUID;

/**
 * Utility class responsible for generating unique multipart boundary values
 * for {@code multipart/form-data} HTTP requests.
 * 
 * @author Lucas Alexandre Bez Piancoski
 * @since 1.0.0
 */
public class GenerateUUIDBoundary {

	public static String getNewUUIDBoundary() {
		return "----Boundary" + UUID.randomUUID();
	}
}
