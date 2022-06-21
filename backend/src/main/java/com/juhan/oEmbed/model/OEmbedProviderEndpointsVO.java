package com.juhan.oEmbed.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class OEmbedProviderEndpointsVO {
	private List<String> schemes;
	private List<String> formats;
	private String discovery;
	private String url;
}
