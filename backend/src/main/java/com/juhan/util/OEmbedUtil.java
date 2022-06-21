package com.juhan.util;

import java.util.HashMap;
import java.util.Set;

import org.json.JSONArray;

import com.juhan.oEmbed.model.OEmbedProviderEndpointsVO;
import com.juhan.oEmbed.model.OEmbedProviderVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OEmbedUtil {
	
	private static OEmbedUtil oEmbedUtil;
	private static HashMap<String, String> providerUrlMap;
	
	private OEmbedUtil() {
		init();
	}
	
	public static OEmbedUtil getInstance() {
		if (oEmbedUtil == null) {
			oEmbedUtil = new OEmbedUtil();
		}
		return oEmbedUtil;
	}
	
	private void init() {
		makeProviderInfo();
	}
	
	/**
	 * Method Desc : provider 정보 생성
	 * @author : Juhan
	 */
	private void makeProviderInfo() {
		log.info("[Provider info 생성 시작]");
		
		providerUrlMap = new HashMap<String, String>();
		
		JsonUtil jsonUtil = JsonUtil.getInstance();
		
		String jsonPath = "oEmbed/providers.json";
		JSONArray providerJsonArr = jsonUtil.readJsonArrFile(jsonPath);
		
		for(Object providerJson : providerJsonArr) {
			OEmbedProviderVO providerInfo = jsonUtil.jsonToObj(providerJson.toString(), OEmbedProviderVO.class);
			
			// endpoints의 정보에서 schemes를 기준으로 url 저장 
			for(OEmbedProviderEndpointsVO endpoint : providerInfo.getEndpoints()) {
				if(endpoint.getSchemes() != null) {
					for(String scheme : endpoint.getSchemes()) {
						providerUrlMap.put(scheme, endpoint.getUrl());
					}
				}
			}
		}
		
		log.info("[Provider info 생성 완료]");
		log.info("[ProviderUrlMap] {} ", providerUrlMap);
	}
	
	/**
	 * Method Desc : provider URL 정보 반환
	 * @author : Juhan
	 * @return
	 */
	public String getProviderUrl(String reqUrl) {
		if(providerUrlMap.isEmpty()) makeProviderInfo();
		log.info("[provider URL 요청] [요청 URL : {}]", reqUrl);
		
		String returnUrl = "";
		Set<String> schemeSet = providerUrlMap.keySet();
		
		// 입력받은 url 과 코드 안에 있는 url 비교하기
		for(String scheme : schemeSet) {
			String[] schemeStr = scheme.split("\\*");
			int schemeStrIdx = 0;
			while(schemeStrIdx < schemeStr.length) {
				if(reqUrl.contains(schemeStr[schemeStrIdx]))
					schemeStrIdx++;
				else
					break;
			}
			
			if(schemeStrIdx == schemeStr.length) {
				returnUrl = providerUrlMap.get(scheme);
				break;
			}
		}
		log.info("[provider URL 요청] [반환 URL : {}]", returnUrl);
		return returnUrl;
	}
}
