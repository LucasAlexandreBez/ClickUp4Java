package api.attachments;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Optional;

import java.util.concurrent.CompletableFuture;

import engine.API_URL_BASE;
import engine.HttpClientProvider;
import engine.HttpRequestProvider;
import helpers.GenerateBodyPublisherForRequestWithFile;
import helpers.GenerateUUIDBoundary;
import helpers.HttpStatusValidator;
import helpers.ResolveJsonMappingToObjects;
import responses.CreateTaskAttachmentResponse;

/**
 * Client responsible for creating task attachments via HTTP API.
 * 
 * @author Lucas Alexandre Bez Piancoski
 * @since 1.0.0
 */
public class CreateTaskAttachments {

	private String buildUrl(String taskId, Optional<String> teamId) {
		return teamId
				.map(id -> API_URL_BASE.TASK_API_URL_BASE.getUrl() + taskId
						+ "/attachment?custom_task_ids=true&team_id=" + id)
				.orElse(API_URL_BASE.TASK_API_URL_BASE.getUrl() + taskId + "/attachment");
	}

	/**
	 * Creates a task attachment synchronously.
	 *
	 * <p><b>Recommended for tests:</b> In UI/E2E automation (Playwright/Selenium/etc...) prefer this synchronous
	 * method for simplicity and determinism. Parallelism is typically handled by the test runner
	 * (workers/threads), so blocking here is usually fine and easier to debug.</p>
	 *
	 * <p><b>Token and IDs:</b>
	 * If {@code clickup_token} is empty, the request may rely on an environment-based token
	 * (depending on your {@link HttpRequestProvider} implementation).
	 * If you are using custom task IDs, provide {@code team_id}; otherwise keep it empty.</p>
	 *
	 * @param task_id       task identifier (or custom task id when enabled)
	 * @param file_path     local path to the file to upload
	 * @param clickup_token optional ClickUp API token
	 * @param team_id       optional team identifier (required when using custom task ids)
	 *
	 * @return parsed {@link CreateTaskAttachmentResponse}
	 *
	 * @throws URISyntaxException   if the generated URL is not a valid URI
	 * @throws IOException          if building the multipart body fails, the request fails,
	 *                              or the API responds with a non-2xx status
	 * @throws InterruptedException if the calling thread is interrupted while waiting
	 */
	public CreateTaskAttachmentResponse callSyncCreateTaskAttachmentAPI(String task_id, String file_path,
			Optional<String> clickup_token, Optional<String> team_id)
			throws URISyntaxException, IOException, InterruptedException {
		final String boundary = GenerateUUIDBoundary.getNewUUIDBoundary();
		BodyPublisher body = GenerateBodyPublisherForRequestWithFile.generateRequestWithFileAsBody(boundary, file_path);
		HttpRequest request = new HttpRequestProvider()
				.generateHttpRequestForPOSTWithFileAsBody(buildUrl(task_id, team_id), boundary, body, clickup_token);
		HttpResponse<String> response = HttpClientProvider.getHttpClient().send(request, BodyHandlers.ofString());
		HttpStatusValidator.checkStatus(response);
		return ResolveJsonMappingToObjects.convertJSON(response.body(), CreateTaskAttachmentResponse.class);
	}

	/**
	 * Creates a task attachment asynchronously.
	 *
	 * <p><b>Recommended for tests:</b> In UI/E2E automation (Playwright/Selenium) prefer the synchronous
	 * method {@code callSyncCreateTaskAttachmentAPI(...)} for simplicity and determinism. Use this async
	 * method mainly when you want to start multiple API calls in parallel and combine them.</p>
	 *
	 * <p>This method returns immediately with a {@link CompletableFuture}. Network failures, non-2xx HTTP
	 * responses, and JSON mapping issues are reported by completing the future exceptionally (typically
	 * wrapped in a {@link java.util.concurrent.CompletionException}).</p>
	 *
	 * <p><b>Token and IDs:</b>
	 * If {@code clickup_token} is empty, the request may rely on an environment-based token
	 * (depending on your {@link HttpRequestProvider} implementation).
	 * If you are using custom task IDs, provide {@code team_id}; otherwise keep it empty.</p>
	 *
	 * <p><b>Example (blocking at the call site):</b></p>
	 * <pre>{@code
	 * CreateTaskAttachmentResponse resp =
	 *     client.callAsyncCreateTaskAttachmentAPI(taskId, filePath, token, teamId).join();
	 * }</pre>
	 *
	 * @param task_id       task identifier (or custom task id when enabled)
	 * @param file_path     local path to the file to upload
	 * @param clickup_token optional ClickUp API token
	 * @param team_id       optional team identifier (required when using custom task ids)
	 *
	 * @return a future that completes with the parsed {@link CreateTaskAttachmentResponse}
	 *
	 * @throws URISyntaxException if the generated URL is not a valid URI
	 * @throws IOException        if building the multipart body fails before the async request is started
	 */
	public CompletableFuture<CreateTaskAttachmentResponse> callAsyncCreateTaskAttachmentAPI(String task_id,
			String file_path, Optional<String> clickup_token, Optional<String> team_id)
			throws URISyntaxException, IOException {

		final String boundary = GenerateUUIDBoundary.getNewUUIDBoundary();
		BodyPublisher body = GenerateBodyPublisherForRequestWithFile.generateRequestWithFileAsBody(boundary, file_path);

		HttpRequest request = new HttpRequestProvider()
				.generateHttpRequestForPOSTWithFileAsBody(buildUrl(task_id, team_id), boundary, body, clickup_token);

		return HttpStatusValidator
				.checkStatus(HttpClientProvider.getHttpClient().sendAsync(request, BodyHandlers.ofString()))
				.thenApply(resp -> ResolveJsonMappingToObjects.convertJSON(resp.body(), CreateTaskAttachmentResponse.class));
	}

}
