package com.juhan.oEmbed.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class OEmbedProviderVO {
	private String provider_name;
	private String provider_url;
	private List<OEmbedProviderEndpointsVO> endpoints;
}
