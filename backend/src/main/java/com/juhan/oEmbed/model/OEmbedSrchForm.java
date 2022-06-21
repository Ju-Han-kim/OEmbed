package com.juhan.oEmbed.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class OEmbedSrchForm {
	private String url;			// 요청 URL
	private String maxwidth;	// 최대 width
	private String maxheight;	// 최대 height
	private String format;		// 요청 format
}
