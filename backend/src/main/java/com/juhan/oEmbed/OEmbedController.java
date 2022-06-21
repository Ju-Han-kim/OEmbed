package com.juhan.oEmbed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.juhan.common.ReturnClass;
import com.juhan.oEmbed.model.OEmbedReturnVO;
import com.juhan.oEmbed.model.OEmbedSrchForm;
import com.juhan.oEmbed.service.OEmbedService;

@Controller
public class OEmbedController {
	
	@Autowired
	private OEmbedService oEmbedService;
	
	/**
	 * Method Desc : oEmbed frame 호출
	 * @author : Juhan
	 * @return
	 */
	@RequestMapping(path = "/oembed", method = RequestMethod.GET)
	public ModelAndView frameOEmbedSearch() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("oEmbed/frameOEmbedSearch");
		
		return mav;
	}
	
	/**
	 * Method Desc : oEmbed view 호출
	 * @author : Juhan
	 * @return
	 */
	@RequestMapping(path = "/oembed/info", method = RequestMethod.POST)
	public ModelAndView viewOEmbedSearch(OEmbedSrchForm oEmbedSrchForm) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("oEmbed/sub/viewOEmbedSearch");
		
		ReturnClass<OEmbedReturnVO> returnClass = oEmbedService.getOEmbedInfo(oEmbedSrchForm);
		
		mav.addObject("OEmbedInfo", returnClass.getObj());
		mav.addObject("message", returnClass.getMessage());
		
		return mav;
	}
}
