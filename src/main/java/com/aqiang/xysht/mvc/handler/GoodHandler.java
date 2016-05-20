package com.aqiang.xysht.mvc.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aqiang.xysht.entities.Classfy;
import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.Good;
import com.aqiang.xysht.service.ClassfyService;
import com.aqiang.xysht.service.GoodService;
import com.aqiang.xysht.service.SupermarketService;
import com.aqiang.xysht.service.TagService;

@Controller
@RequestMapping("shopKeeper")
public class GoodHandler {

	@Autowired
	private GoodService goodService;
	@Autowired
	private ClassfyService classfyService;
	@Autowired
	private TagService tagService;
	@Autowired
	private SupermarketService supermarketService;
	private static final Logger LOGGER = LoggerFactory.getLogger(GoodHandler.class);
	private List<ErrorMessage> errorMessages;

	@RequestMapping("getGoodsByClassfy")
	public String list(Map<String, Object> map, Integer classfyId) {
		Classfy classfy = classfyService.findEntity(classfyId);
		List<Good> goods = goodService.getAllGoodsByClassfy(classfy);
		map.put("goods", goods);
		map.put("classfy", classfy);
		map.put("classfies", classfyService.getClassfiesByParent(classfy.getSupermarket(), classfy));
		return "good/list";
	}

	@RequestMapping("editGood")
	public String editUI(Integer classfyId, Map<String, Object> map) {
		map.put("classfy", classfyService.findEntity(classfyId));
		map.put("tags", tagService.getAllTags());
		return "good/editUI";
	}

	@RequestMapping("saveGood")
	public String save(Map<String, Object> map, Good good, Integer[] tagsId,
			@RequestParam(value = "p", required = false) MultipartFile p) {
		errorMessages = new ArrayList<ErrorMessage>();
		goodService.validateGood(good, errorMessages);
		// TODO replace this code smile
		good.setSupermarket(classfyService.findEntity(good.getClassfy().getId()).getSupermarket());
		LOGGER.info("in goodhandler page good is : {}", good);
		if (errorMessages.size() == 0) {
			goodService.updateGood(good, tagsId, p);
			return "redirect:/shopKeeper/getGoodsByClassfy?classfyId=" + good.getClassfy().getId();
		} else {
			map.put("classfy", classfyService.findEntity(good.getClassfy().getId()));
			map.put("tags", tagService.getAllTags());
			map.put("errorMessages", errorMessages);
			return "good/editUI";
		}

	}

	@RequestMapping("deleteGood")
	public String delete(Integer id, Integer classfyId) {
		goodService.delete(goodService.findEntity(id));
		return "redirect:/shopKeeper/getGoodsByClassfy?classfyId=" + classfyId;
	}

	@ModelAttribute
	public void getGood(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
		Good good = null;
		if (id != null) {
			good = goodService.findEntity(id);
		} else {
			good = new Good();
		}
		map.put("good", good);
	}
}
