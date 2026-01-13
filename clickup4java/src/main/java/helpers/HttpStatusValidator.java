package helpers;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * Utility class for validating HTTP status codes.
 * @author Lucas Alexandre Bez Piancoski
 * @since 1.0.0
 */
public class HttpStatusValidator {

    private static final int MAX_BODY_CHARS = 1000;

    private HttpStatusValidator() {}

    private static String convertBodyToString(String body) {
        if (body == null) return "<empty>";
        return body.length() > MAX_BODY_CHARS
                ? body.substring(0, MAX_BODY_CHARS) + "...(truncated)"
                : body;
    }

    /**
     * Check if response is with status between 2xx.
     * @param response the {@link HttpResponse} from request
     * @return the {@link HttpResponse}
     * @throws IOException if the status is not between 2xx
     */
    public static HttpResponse<String> checkStatus(HttpResponse<String> response) throws IOException {
        Objects.requireNonNull(response, "response");
        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("HTTP request failed. Status: " + response.statusCode() + ", Body: " + convertBodyToString(response.body()));
        }
        return response;
    }

    /**
     * Check if response is with status between 2xx.
     * @param response the {@link HttpResponse} from request
     * @return the {@link HttpResponse}
     * @throws IOException if the status is not between 2xx
     */
    public static CompletableFuture<HttpResponse<String>> checkStatus(CompletableFuture<HttpResponse<String>> future) {
        Objects.requireNonNull(future, "future");
        return future.thenApply(response -> {
            try {
                return checkStatus(response);
            } catch (IOException e) {
                throw new CompletionException(e);
            }
        });
    }
}
