package com.github.lucasalexandrebez.connections;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Map;
import java.util.Objects;

public class ClickUpConnFactoryV3 {

    public enum ApiVersion {
        V2("v2"),
        V3("v3");

        private final String path;
        ApiVersion(String path) { this.path = path; }
        public String path() { return path; }
    }

    public enum HttpMethod {
        GET, POST, PUT, DELETE
    }

    private static final String BASE_URL = "https://api.clickup.com/api/";

    public HttpRequest buildRequest(
            ApiVersion version,
            HttpMethod method,
            String path,
            Map<String, String> headers,
            String jsonBody // pode ser null
    ) {
        Objects.requireNonNull(version, "version");
        Objects.requireNonNull(method, "method");
        Objects.requireNonNull(path, "path");

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + version.path() + "/" + path));

        // aplica headers
        if (headers != null) {
            headers.forEach(builder::header);
        }

        // escolhe método
        switch (method) {
            case GET -> builder.GET();

            case DELETE -> builder.DELETE();

            case POST -> builder.POST(
                    jsonBody == null
                            ? HttpRequest.BodyPublishers.noBody()
                            : HttpRequest.BodyPublishers.ofString(jsonBody)
            );

            case PUT -> builder.PUT(
                    jsonBody == null
                            ? HttpRequest.BodyPublishers.noBody()
                            : HttpRequest.BodyPublishers.ofString(jsonBody)
            );
        }

        return builder.build();
    }
}
