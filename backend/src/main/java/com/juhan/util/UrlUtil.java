package com.juhan.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.regex.Pattern;

import com.juhan.common.ReturnClass;
import com.juhan.config.Constant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UrlUtil {
	
	private static UrlUtil urlUtil;
	
	private UrlUtil() {}
	
	public static UrlUtil getInstance() {
		if (urlUtil == null) {
			urlUtil = new UrlUtil();
		}
		return urlUtil;
	}
	
	/*
	 * Method Desc : URL 요청 결과 반환 
	 * @author : Juhan
	 * @return
	 */
	public ReturnClass<String> urlConnectionGET(String reqUrl, HashMap<String, String> param, String reqFormat) {
		log.info("[URL GET 요청] [요청 URL {} / paramMap {} / reqFormat {}]", reqUrl, param, reqFormat);
		if(!validateUrl(reqUrl)) {
			return new ReturnClass<String>(Constant.FAIL, "[요청실패] URL을 확인해주세요.", null);
		}
		
		URL url;
		HttpURLConnection conn = null;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		
		String resCode = "";
		String resData = "";
		
		try {
			url = new URL(reqUrl + getParamStr(param));
			
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type","utf-8");
			conn.setRequestProperty("Accept", "application/"+reqFormat);
			
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));	
			
			String line;
		    while ((line = br.readLine()) != null) {
		        sb.append(line);
		    }
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnClass<String>(Constant.FAIL, "[요청실패] 기타오류! URL 요청에 실패했습니다.", null);
		} finally {
			try {
				// 응답 결과
				resCode = String.valueOf(conn.getResponseCode());
				resData = sb.toString();
				log.info("[URL GET 요청] [응답 결과] [{}]", resCode);
				log.info("[URL GET 요청] [응답 데이터] [{}]", resData);
				
				conn.disconnect();
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return new ReturnClass<String>(Constant.SUCCESS, "URL 요청 성공", resData);
	}
	
	/*
	 * Method Desc : URL 양식을 검증하여 값 양식이 맞는 경우 TRUE를 return
	 * @author : Juhan
	 * @param url
	 * @return
	 */
	private Boolean validateUrl(String url) {
		// null 검증
		if(url == null || "".equals(url))
			return false;
		
		// 정규식을 이용한 양식 검증
		String regex = "^(https?):\\/\\/([^:\\/\\s]+)(:([^\\/]*))?((\\/[^\\s/\\/]+)*)?\\/?([^#\\s\\?]*)(\\?([^#\\s]*))?(#(\\w*))?$";
        Pattern p = Pattern.compile(regex);
		
    	return p.matcher(url).matches();
	}
	
	/*
	 * Method Desc : Map을 받아 Param형식으로 return
	 * @author : Juhan
	 * @return
	 */
	private String getParamStr(HashMap<String, String> paramMap) {
		StringBuilder sb = new StringBuilder();
		try {
			for(String key : paramMap.keySet()) {
				if("".equals(sb.toString())) {
					sb.append("?");
				} else {
					sb.append("&");
				}
				
				sb.append(key).append("=").append(URLEncoder.encode(paramMap.get(key)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
		return sb.toString();
	}
}
