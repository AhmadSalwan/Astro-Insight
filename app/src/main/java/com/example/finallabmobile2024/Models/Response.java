package com.example.finallabmobile2024.Models;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("date")
	private String date;

	@SerializedName("explanation")
	private String explanation;
	@SerializedName("media_type")
	private String mediaType;

	@SerializedName("service_version")
	private String serviceVersion;

	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getServiceVersion() {
		return serviceVersion;
	}

	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}