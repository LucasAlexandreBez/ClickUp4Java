package engine;

public enum API_URL_BASE {
	CLICKUPV2_API_URL_BASE("https://api.clickup.com/api/v2/"),
	TASK_API_URL_BASE(CLICKUPV2_API_URL_BASE.getUrl() + "task/");
	
	private final String url;
	
	API_URL_BASE(String url){
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
}
