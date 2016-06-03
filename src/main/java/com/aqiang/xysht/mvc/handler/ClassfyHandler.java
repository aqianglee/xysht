package com.aqiang.xysht.mvc.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aqiang.xysht.entities.Classfy;
import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.Supermarket;
import com.aqiang.xysht.service.ClassfyService;
import com.aqiang.xysht.service.SupermarketService;

@Controller
@RequestMapping("shopKeeper")
public class ClassfyHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassfyHandler.class);

	private List<ErrorMessage> errorMessages;
	@Autowired
	private ClassfyService classfyService;
	@Autowired
	private SupermarketService supermarketService;

	@RequestMapping("listAllClassfies")
	public String list(Map<String, Object> map, int sid, Integer parentId) {
		Classfy parent = null;
		if (parentId != null) {
			parent = classfyService.findEntity(parentId);
		}
		List<Classfy> classfies = classfyService.getClassfiesByParent(supermarketService.findEntity(sid), parent);
		map.put("classfies", classfies);
		map.put("sid", sid);
		map.put("parent", parent);
		return "classfy/list";
	}

	@RequestMapping("editClassfy")
	public String editUI(Map<String, Object> map, HttpSession session, Integer supermarketId, Integer parentId) {
		Classfy parent = null;
		if (parentId != null) {
			parent = classfyService.findEntity(parentId);
		}
		Supermarket supermarket = null;
		if (supermarketId != null) {
			supermarket = supermarketService.findEntity(supermarketId);
		}
		map.put("p", parent);
		map.put("s", supermarket);
		return "classfy/editUI";
	}

	@RequestMapping("saveClassfy")
	public String save(Map<String, Object> map, Classfy classfy) {
		errorMessages = new ArrayList<ErrorMessage>();
		classfyService.validateClassfy(classfy, errorMessages);
		if (errorMessages.size() == 0) {
			LOGGER.info("classfy id is {}", classfy.getParent().getId());
			classfyService.updateClassfy(classfy);
			return String.format("redirect:/shopKeeper/listAllClassfies?sid=%s&parentId=%s", classfy.getSupermarket()
					.getId() == null ? "" : classfy.getSupermarket().getId(), classfy.getParent() == null
					|| classfy.getParent().getId() == null ? "" : classfy.getParent().getId());
		} else {
			map.put("errorMessages", errorMessages);
			map.put("p", classfy.getParent());
			map.put("s", classfy.getSupermarket());
			return "classfy/editUI";
		}
	}

	@RequestMapping("deleteClassfy")
	public String delete(int id, int sid) {
		Classfy classfy = classfyService.findEntity(id);
		classfyService.deleteEntity(id);
		return String.format("redirect:/shopKeeper/listAllClassfies?sid=%s&parentId=%s", sid,
				classfy.getParent() == null || classfy.getParent().getId() == null ? "" : classfy.getParent().getId());
	}

	@ModelAttribute
	public void getClassfy(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
		Classfy classfy = null;
		if (id != null) {
			classfy = classfyService.findEntity(id);
		} else {
			classfy = new Classfy();
		}
		map.put("classfy", classfy);
	}
}
