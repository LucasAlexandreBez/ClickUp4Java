package engine;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.util.Optional;

import helpers.ResolveClickUpTokenFromSystemVariable;

/**
 * Utility class responsible for building {@link HttpRequest} instances
 * with a consistent configuration for the ClickUp API calls.
 * 
 * @author Lucas Alexandre Bez Piancoski
 * @since 1.0.0
 */
public class HttpRequestProvider {

    /**
     * Creates a base {@link HttpRequest.Builder} with the given URL and token to be used for other functions on this class.
     *
     * @param url   the target URL as a string
     * @param token the authorization token to be used in the {@code Authorization} header
     * 
     * @return a pre-configured {@link HttpRequest.Builder}
     * @throws URISyntaxException if the URL string is not a valid URI
     */
    private HttpRequest.Builder generateHttpRequestBase(String url, String token) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Authorization", token)
                .header("Accept", "application/json");
    }

    // ========================== GET ==========================
    /**
     * Builds a {@code GET} request using the provided URL and an authorization token.
     * <br>
     * <b>If the token is present, it will be used. Otherwise, the token is resolved from
     * the {@link ResolveClickUpTokenFromSystemVariable#getClickUpToken()} environment variable.
     * </b>
     *
     * @param url the target URL as a string
     * @param clickup_token optional clickup authorization token
     * 
     * @return a configured {@link HttpRequest} for a GET operation
     * @throws URISyntaxException if the URL string is not a valid URI
     */
    public HttpRequest generateHttpRequestForGET(String url, Optional<String> clickup_token) throws URISyntaxException {
    	return generateHttpRequestBase(url, clickup_token.orElseGet(ResolveClickUpTokenFromSystemVariable::getClickUpToken))
    	                    .GET()
    	                    .build();	
    }
    
    // ========================== DELETE ==========================
    /**
     * Builds a {@code DELETE} request using the provided URL and an authorization token.
     * <br>
     * <b>If the token is present, it will be used. Otherwise, the token is resolved from
     * the {@link ResolveClickUpTokenFromSystemVariable#getClickUpToken()} environment variable.
     * </b>
     *
     * @param url the target URL as a string
     * @param clickup_token optional clickup authorization token
     * 
     * @return a configured {@link HttpRequest} for a DELETE operation
     * @throws URISyntaxException if the URL string is not a valid URI

     */
    public HttpRequest generateHttpRequestForDELETE(String url, Optional<String> clickup_token) throws URISyntaxException {
        return generateHttpRequestBase(url, clickup_token.orElseGet(ResolveClickUpTokenFromSystemVariable::getClickUpToken))
                .DELETE()
                .build();
    }

    // ========================== POST ==========================
    /**
     * Builds a {@code POST} request for uploading attachments using
     * {@code multipart/form-data} as the content type.
     * <br>
     * <b>If the token is present, it will be used. Otherwise, the token is resolved from
     * the {@link ResolveClickUpTokenFromSystemVariable#getClickUpToken()} environment variable.
     * </b>
     *
     * @param url  the target URL as a string
     * @param boundary the server use to know where to cut the content when start to read the request.
     * @param body the request body publisher, typically representing a multipart payload
     * @param clickup_token optional clickup authorization token
     * 
     * @return a configured {@link HttpRequest} for a multipart POST operation
     * @throws URISyntaxException if the URL string is not a valid URI
     */
    public HttpRequest generateHttpRequestForPOSTWithFileAsBody(String url, String boundary, BodyPublisher body, Optional<String> clickup_token) throws URISyntaxException {    
    	return generateHttpRequestBase(url, clickup_token.orElseGet(ResolveClickUpTokenFromSystemVariable::getClickUpToken))
            .header("Content-Type", "multipart/form-data; boundary=" + boundary)
            .POST(body)
            .build();
    }

    /**
     * Builds a {@code POST} request for JSON payloads using
     * {@code application/json} as the content type.
     * <br>
     * <b>If the token is present, it will be used. Otherwise, the token is resolved from
     * the {@link ResolveClickUpTokenFromSystemVariable#getClickUpToken()} environment variable.
     * </b>
     *
     * @param url  the target URL as a string
     * @param body the request body publisher, typically representing a JSON payload
     * @param clickup_token optional clickup authorization token
     * 
     * @return a configured {@link HttpRequest} for a JSON POST operation
     * @throws URISyntaxException if the URL string is not a valid URI
     */
    public HttpRequest generateHttpRequestForPOST(String url, BodyPublisher body, Optional<String> clickup_token) throws URISyntaxException {
        return generateHttpRequestBase(url, clickup_token.orElseGet(ResolveClickUpTokenFromSystemVariable::getClickUpToken))
                .header("Content-Type", "application/json")
                .POST(body)
                .build();
    }

    // ========================== PUT ==========================

    /**
     * Builds a {@code PUT} request for updating attachments using
     * {@code multipart/form-data} as the content type.
     * <br>
     * <b>If the token is present, it will be used. Otherwise, the token is resolved from
     * the {@link ResolveClickUpTokenFromSystemVariable#getClickUpToken()} environment variable.
     * </b>
     * @param url  the target URL as a string
     * @param boundary the server use to know where to cut the content when start to read the request.
     * @param body the request body publisher, typically representing a multipart payload
     * @param clickup_token optional clickup authorization token
     * 
     * @return a configured {@link HttpRequest} for a multipart PUT operation
     * @throws URISyntaxException if the URL string is not a valid URI
     */
    public HttpRequest generateHttpRequestForPUTWithFileAsBody(String url, String boundary, BodyPublisher body, Optional<String> clickup_token) throws URISyntaxException {    
    	return generateHttpRequestBase(url, clickup_token.orElseGet(ResolveClickUpTokenFromSystemVariable::getClickUpToken))
            .header("Content-Type", "multipart/form-data; boundary=" + boundary)
            .PUT(body)
            .build();
    }

    /**
     * Builds a {@code PUT} request for JSON payloads using
     * {@code application/json} as the content type.
     * <br>
     * <b>If the token is present, it will be used. Otherwise, the token is resolved from
     * the {@link ResolveClickUpTokenFromSystemVariable#getClickUpToken()} environment variable.
     * </b>
     *
     * @param url  the target URL as a string
     * @param body the request body publisher, typically representing a JSON payload
     * @param clickup_token optional clickup authorization token
     * 
     * @return a configured {@link HttpRequest} for a JSON PUT operation
     * @throws URISyntaxException if the URL string is not a valid URI
     */
    public HttpRequest generateHttpRequestForPUT(String url, BodyPublisher body, Optional<String> clickup_token) throws URISyntaxException {
        return generateHttpRequestBase(url, clickup_token.orElseGet(ResolveClickUpTokenFromSystemVariable::getClickUpToken))
                .header("Content-Type", "application/json")
                .PUT(body)
                .build();
    }
    
}
