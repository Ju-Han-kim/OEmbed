package com.juhan.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.springframework.core.io.ClassPathResource;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtil {
	private static JsonUtil jsonUtil;
	
	private JsonUtil() {}
	
	public static JsonUtil getInstance() {
		if (jsonUtil == null) {
			jsonUtil = new JsonUtil();
		}
		return jsonUtil;
	}
	
	/**
	 * Method Desc : 파일경로를 받아 JsonArr을 리턴. ( 없는경우 Null 반환 )
	 * @author : Juhan
	 * @param path
	 * @return
	 */
	public JSONArray readJsonArrFile(String path) {
		if(path == null || "".equals(path)) {
			log.debug("[jsonArray 반환 실패] : 경로를 확인하세요");
			return null;
		}
		
		JSONArray jsonArr = null;
		
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		
		 try {
	    	ClassPathResource classPathResource = new ClassPathResource(path);
			br = new BufferedReader(new InputStreamReader(classPathResource.getInputStream()));
			
			String line;
		    while ((line = br.readLine()) != null) {
		        sb.append(line);
		    }
		    
		    jsonArr = new JSONArray(sb.toString());
		    
		} catch (IOException e) {
			log.debug("[jsonArray 반환 실패] : 파일 변환 오류");
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				log.debug("[jsonArray 반환 실패] : 메모리 할당 해제 오류");
				e.printStackTrace();
			}
		}
		
		log.info("[jsonArray 반환 성공] [path : {}]", path);
		return jsonArr;
	}
	
	/**
	 * Method Desc : Json을 객체에 매핑하여 return
	 * @author : Juhan
	 * @param jsonData
	 * @param obj
	 * @return 
	 * @return
	 */
	public <T> T jsonToObj(String jsonData, Class<T> clazz) {
		Gson gson = new Gson();
		return gson.fromJson(jsonData, clazz);
	}
}
