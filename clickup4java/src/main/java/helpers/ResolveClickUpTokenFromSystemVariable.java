package helpers;

/**
 * Resolves the ClickUp API token from the environment.
 * 
 * @author Lucas Alexandre Bez Piancoski
 * @since 1.0.0
 */
public class ResolveClickUpTokenFromSystemVariable {
	
	private ResolveClickUpTokenFromSystemVariable() {}
	
    /**
     * This method reads the {@code CLICKUP_TOKEN} environment variable and
     * validates that it is present and not blank.
     *
     * @return the resolved token value
     * @throws IllegalArgumentException if the environment variable is missing or blank
     */
    public static String getClickUpToken() {
        String token = System.getenv("CLICKUP_TOKEN");
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException(
                "Unable to read CLICKUP_TOKEN from environment. " +
                "Please check that the environment variable 'CLICKUP_TOKEN' is defined and accessible."
            );
        }
        return token;
    }

}
