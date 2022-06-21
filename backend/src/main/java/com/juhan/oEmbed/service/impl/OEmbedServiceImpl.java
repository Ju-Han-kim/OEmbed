package com.juhan.oEmbed.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.juhan.common.ReturnClass;
import com.juhan.config.Constant;
import com.juhan.oEmbed.model.OEmbedReturnVO;
import com.juhan.oEmbed.model.OEmbedSrchForm;
import com.juhan.oEmbed.service.OEmbedService;
import com.juhan.util.JsonUtil;
import com.juhan.util.OEmbedUtil;
import com.juhan.util.UrlUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OEmbedServiceImpl implements OEmbedService {
	
	/**
	 * Method Desc : OEmbed 조회 결과 반환
	 * @author : Juhan
	 * @param oEmbedSrchForm
	 * @return
	 */
	@Override
	public ReturnClass<OEmbedReturnVO> getOEmbedInfo(OEmbedSrchForm oEmbedSrchForm) {
		HashMap<String, String> param = new HashMap<String, String>();
		
		String reqUrl = OEmbedUtil.getInstance().getProviderUrl(oEmbedSrchForm.getUrl());
		
		param.put("url", oEmbedSrchForm.getUrl());
		param.put("maxwidth", oEmbedSrchForm.getMaxwidth());
		param.put("maxheight", oEmbedSrchForm.getMaxheight());
		if(reqUrl.contains("{format}")) {
			reqUrl = reqUrl.replace("{format}", oEmbedSrchForm.getFormat());
		} else {
			param.put("format", oEmbedSrchForm.getFormat());
		}
		
		ReturnClass<String> returnClass = UrlUtil.getInstance().urlConnectionGET(reqUrl, param, oEmbedSrchForm.getFormat());
		log.info("[URL 요청 결과 메세지] [{}]", returnClass.getMessage());
		
		if(Constant.SUCCESS.equals(returnClass.getResult())) {
			OEmbedReturnVO resObj = null;
			
			// JSON Parse
			if("json".equals(oEmbedSrchForm.getFormat())) {
				resObj = JsonUtil.getInstance().jsonToObj(returnClass.getObj(), OEmbedReturnVO.class);
			}
			
			return new ReturnClass<OEmbedReturnVO>(Constant.SUCCESS, returnClass.getMessage(), resObj);
		} else {
			return new ReturnClass<OEmbedReturnVO>(Constant.FAIL, returnClass.getMessage(), null);
		}
	}
	
}
