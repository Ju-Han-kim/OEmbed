package com.juhan.oEmbed.service;

import com.juhan.common.ReturnClass;
import com.juhan.oEmbed.model.OEmbedReturnVO;
import com.juhan.oEmbed.model.OEmbedSrchForm;

public interface OEmbedService {
	
	/**
	 * Method Desc : OEmbed 조회 결과 객체 반환
	 * @author : Juhan
	 * @param oEmbedSrchForm
	 * @return
	 */
	public ReturnClass<OEmbedReturnVO> getOEmbedInfo(OEmbedSrchForm oEmbedSrchForm);
}
