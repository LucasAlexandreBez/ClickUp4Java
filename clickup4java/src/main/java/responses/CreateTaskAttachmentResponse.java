package responses;

import java.time.Instant;

public class CreateTaskAttachmentResponse {

	private String id;
	private String version;
	private long date;
	private String name;
	private String title;
	private String extension;
	private long source;
	private String thumbnail_small;
	private String thumbnail_medium;
	private String thumbnail_large;
	private String url;
	private String url_w_query;
	private String url_w_host;
	
	public CreateTaskAttachmentResponse() {
		super();
	}

	public CreateTaskAttachmentResponse(String id, String version, long date, String name, String title,
			String extension, long source, String thumbnail_small, String thumbnail_medium, String thumbnail_large,
			String url, String url_w_query, String url_w_host) {
		super();
		this.id = id;
		this.version = version;
		this.date = date;
		this.name = name;
		this.title = title;
		this.extension = extension;
		this.source = source;
		this.thumbnail_small = thumbnail_small;
		this.thumbnail_medium = thumbnail_medium;
		this.thumbnail_large = thumbnail_large;
		this.url = url;
		this.url_w_query = url_w_query;
		this.url_w_host = url_w_host;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Instant getDate() {
		return Instant.ofEpochMilli(date);
	}

	public void setDate(long  date) {
		this.date = date;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public long getSource() {
		return source;
	}
	
	public void setSource(long source) {
		this.source = source;
	}

	public String getThumbnail_small() {
		return thumbnail_small;
	}

	public void setThumbnail_small(String thumbnail_small) {
		this.thumbnail_small = thumbnail_small;
	}
	
	public String getThumbnail_medium() {
		return thumbnail_medium;
	}
	
	public void setThumbnail_medium(String thumbnail_medium) {
		this.thumbnail_medium = thumbnail_medium;
	}

	public String getThumbnail_large() {
		return thumbnail_large;
	}

	public void setThumbnail_large(String thumbnail_large) {
		this.thumbnail_large = thumbnail_large;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl_w_query() {
		return url_w_query;
	}
	
	public void setUrl_w_query(String url_w_query) {
		this.url_w_query = url_w_query;
	}
	
	public String getUrl_w_host() {
		return url_w_host;
	}
	
	public void setUrl_w_host(String url_w_host) {
		this.url_w_host = url_w_host;
	}
	
}
