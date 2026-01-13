package attachments;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.Parameter;
import org.junit.jupiter.params.ParameterizedClass;
import org.junit.jupiter.params.provider.ValueSource;

import api.attachments.CreateTaskAttachments;
import config.ResolveProperties;
import responses.CreateTaskAttachmentResponse;

import static org.junit.jupiter.api.Assertions.*;

@ParameterizedClass
@ValueSource(strings = { "text.txt", "image.jpg", "audio.mp3", "video.mp4", "excel.xlsx", "word.docx", "powerpoint.pptx", "winrar.zip" })
public class CreateTaskAttachmentTest {

	@Parameter
	public String fileName;
	private Optional<String> TOKEN = ResolveProperties.getPropertieValue("clickup.token");

	private void assertAttachmentStructure(CreateTaskAttachmentResponse resp, String fileName) {
		int dotIndex = fileName.lastIndexOf('.');
		String expectedExtension = dotIndex == -1 ? "" : fileName.substring(dotIndex + 1);

		assertAll("attachment structure", () -> assertNotNull(resp.getId()), () -> assertNotNull(resp.getVersion()),
				() -> assertNotNull(resp.getDate()), () -> assertNotNull(resp.getName()),
				() -> assertNotNull(resp.getTitle()), () -> assertNotNull(resp.getExtension()),
				() -> assertNotNull(resp.getSource()), () -> {
					Set<String> previewExt = Set.of("png", "jpg", "jpeg", "gif", "webp", "bmp");
					String ext = resp.getExtension().toLowerCase();
					boolean havePreview = previewExt.contains(ext);
					if (havePreview) {
						assertNotNull(resp.getThumbnail_small());
						assertNotNull(resp.getThumbnail_medium());
						assertNotNull(resp.getThumbnail_large());
					} else {
						assertNull(resp.getThumbnail_small());
						assertNull(resp.getThumbnail_medium());
						assertNull(resp.getThumbnail_large());
					}
				}, () -> assertNotNull(resp.getUrl()), () -> assertNotNull(resp.getUrl_w_query()),
				() -> assertNotNull(resp.getUrl_w_host()), () -> {
					assertEquals(fileName, resp.getTitle());
					assertEquals(fileName, resp.getName());
					assertEquals(expectedExtension, resp.getExtension());
				});
	}

	@Test
	public void TC01_Sync_LocalToken_NoCustomTaskId_uploadAttachmentWithSuccess()
			throws URISyntaxException, IOException, InterruptedException {
		CreateTaskAttachments taskAttachments = new CreateTaskAttachments();
		Path filePath = Paths.get("src/test/resources/files", fileName);
		CreateTaskAttachmentResponse resp = taskAttachments.callSyncCreateTaskAttachmentAPI("86aef6x03",
				filePath.toString(), TOKEN, Optional.empty());
		assertAttachmentStructure(resp, fileName);
	}

	@Test
	public void TC02_Sync_EnvToken_NoCustomTaskId_uploadAttachmentWithSuccess()
			throws URISyntaxException, IOException, InterruptedException {
		CreateTaskAttachments taskAttachments = new CreateTaskAttachments();
		Path filePath = Paths.get("src/test/resources/files", fileName);
		CreateTaskAttachmentResponse resp = taskAttachments.callSyncCreateTaskAttachmentAPI("86aef6x3a",
				filePath.toString(), Optional.empty(), Optional.empty());
		assertAttachmentStructure(resp, fileName);
	}

}
