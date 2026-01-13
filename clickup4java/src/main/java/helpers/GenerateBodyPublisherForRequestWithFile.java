package helpers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.io.UncheckedIOException;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Utility class responsible for building {@link BodyPublisher} instances for multipart/form-data.
 * 
 * @author Lucas Alexandre Bez Piancoski
 * @since 1.0.0
 */
public class GenerateBodyPublisherForRequestWithFile {

	private GenerateBodyPublisherForRequestWithFile() {}

    /**
     * Builds a {@code multipart/form-data} request body containing a single file part.
     *
     * @param boundary the multipart boundary value
     * @param file_path local path to the file to be included in the request body
     * 
     * @return a {@link BodyPublisher} representing the multipart payload
     * @throws IOException if the file cannot be resolved
     */
	@SuppressWarnings("resource")
	public static BodyPublisher generateRequestWithFileAsBody(String boundary, String file_path) throws IOException {
		
	    Path file = ResolveProvidedFilePathFromUser.getFile(file_path);
	    String mimeType = Optional.ofNullable(Files.probeContentType(file)).orElse("application/octet-stream");

	    String prefix =
	            "--" + boundary + "\r\n" +
	            "Content-Disposition: form-data; name=\"attachment\"; filename=\"" + file.getFileName().toString() + "\"\r\n" +
	            "Content-Type: " + mimeType + "\r\n\r\n";

	    String suffix = "\r\n--" + boundary + "--\r\n";

	    return BodyPublishers.ofInputStream(() -> {
	        try {
	            return new SequenceInputStream(
	                    new ByteArrayInputStream(prefix.getBytes(StandardCharsets.UTF_8)),
	                    new SequenceInputStream(
	                            Files.newInputStream(file),
	                            new ByteArrayInputStream(suffix.getBytes(StandardCharsets.UTF_8))
	                    )
	            );
	        } catch (IOException e) {
	            throw new UncheckedIOException(e);
	        }
	    });
	}

}
