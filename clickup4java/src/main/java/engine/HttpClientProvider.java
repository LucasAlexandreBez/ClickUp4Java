package engine;

import java.net.http.HttpClient;
import java.time.Duration;

/**
 * Provides a single shared {@link HttpClient} instance to be used for all API requests.
 * <p>
 * The {@link HttpClient} is <b>immutable</b> and <b>thread-safe</b>, which allows it to be safely reused
 * across the entire library. Reusing a single instance also enables <b>efficient connection
 * pooling</b> and <b>concurrent asynchronous requests</b> without additional configuration.
 * </p>
 *
 * @author Lucas Alexandre Bez Piancoski
 * @since 1.0.0
 */
public final class HttpClientProvider {

    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(30))
            .build();

    private HttpClientProvider() {}

    public static HttpClient getHttpClient() {
        return HTTP_CLIENT;
    }
}
