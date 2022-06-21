package com.juhan.oEmbed.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class OEmbedReturnVO {
	// Common
	private String type;
	private String version;
	private String title;
	private String author_name;
	private String author_url;
	private String provider_name;
	private String provider_url;
	private String cache_age;
	private String thumbnail_url;
	private String thumbnail_width;
	private String thumbnail_height;
	
	// Photo
	private String url;
	private String width;
	private String height;
	
	// Video
	private String html;
}
