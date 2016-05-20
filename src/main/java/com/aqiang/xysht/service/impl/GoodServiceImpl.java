package com.aqiang.xysht.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aqiang.xysht.dao.BaseDao;
import com.aqiang.xysht.entities.Classfy;
import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.Good;
import com.aqiang.xysht.entities.Picture;
import com.aqiang.xysht.entities.Supermarket;
import com.aqiang.xysht.entities.Tag;
import com.aqiang.xysht.service.ClassfyService;
import com.aqiang.xysht.service.GoodService;
import com.aqiang.xysht.service.PictureService;
import com.aqiang.xysht.service.SupermarketService;
import com.aqiang.xysht.service.TagService;
import com.aqiang.xysht.service.ValidateService;

@Service
@Transactional
public class GoodServiceImpl extends BaseServiceImpl<Good> implements GoodService {

	@Autowired
	private PictureService pictureService;
	@Autowired
	private ValidateService validateService;
	@Autowired
	private TagService tagService;
	@Autowired
	private SupermarketService supermarketService;
	@Autowired
	private ClassfyService classfyService;
	private static final Logger LOGGER = LoggerFactory.getLogger(GoodServiceImpl.class);

	@Resource(name = "goodDao")
	@Override
	public void setDao(BaseDao<Good> dao) {
		this.dao = dao;
	}

	@Override
	public List<Good> getAllGoodsByClassfy(Classfy classfy) {
		return findEntityByJpql("From Good g where g.classfy = ?", classfy);
	}

	@Override
	public void updateGood(Good good, Integer[] tagsId, MultipartFile p) {
		Picture picture = null;
		if (p != null) {
			Picture icon = good.getPicture();
			if (icon != null) {
				pictureService.deletePicture(icon);
			}
			picture = pictureService.savePicture(p);
		}
		good.setPicture(picture);
		List<Tag> tags = tagService.getTags(tagsId);
		addTags(good, tags);
		margeEntity(good);
	}

	@Override
	public void delete(Good good) {

	}

	@Override
	public void validateGood(Good good, List<ErrorMessage> messages) {
		validateService.validate(messages, good.getName(), "goodName");
		validateService.validate(messages, good.getPrice(), "price");
		validateService.validate(messages, good.getNumber(), "number");
		validateService.validate(messages, good.getSpecification(), "specification");
		validateService.validate(messages, good.getStockNumber(), "stockNumber");
	}

	@Override
	public void addTag(Good good, Tag tag) {
		if (tag.getId() == null || tagService.findEntity(tag.getId()) == null) {
			throw new RuntimeException();
		}
		LOGGER.info("in good service good is : {}", good);
		List<Tag> tags = getTags(good.getTags());
		tags.add(tag);
		good.setTags(getTagsIdStr(tags));
	}

	private String getTagsIdStr(List<Tag> tags) {
		String tagsIdStr = "";
		for (Tag tag : tags) {
			if (tagsIdStr == "") {
				tagsIdStr += tag.getId();
			} else {
				tagsIdStr = tagsIdStr + "," + tag.getId();
			}
		}
		return tagsIdStr;
	}

	private List<Tag> getTags(String tagsIdStr) {
		List<Tag> tags = new ArrayList<Tag>();
		if (tagsIdStr != null) {
			String[] tagsId = tagsIdStr.split(",");
			for (String tagId : tagsId) {
				tags.add(tagService.findEntity(Integer.parseInt(tagId)));
			}
		}
		return tags;
	}

	@Override
	public void addTags(Good good, List<Tag> tags) {
		if (tags != null) {
			for (Tag tag : tags) {
				addTag(good, tag);
			}
		}
	}

	@Override
	public List<Good> getAllHotGoodBySupermarket(Supermarket supermarket) {
		// TODO rewrite this method
		List<Good> all = getAll();
		for (Good good : all) {
			initTagsList(good);
		}
		return all;
	}

	@Override
	public void initTagsList(Good good) {
		good.setTagsList(getTags(good.getTags()));
	}

	@Override
	public List<Good> getAllDiscountGoodBySupermarket(Supermarket supermarket) {
		// TODO rewrite this method
		List<Good> all = getAll();
		for (Good good : all) {
			initTagsList(good);
		}
		return all;
	}

	@Override
	public List<Good> getHotGoods(Classfy classfy) {
		List<Good> goods = new ArrayList<Good>();
		List<Classfy> classfies = classfyService.getHotClassfiesByName(classfy.getName());
		LOGGER.info("get {} classfies", classfies.size());
		for (Classfy c : classfies) {
			goods.addAll(getHotGoodsByClassfy(c));
		}
		sortGoodsBySalesVolume(goods);
		return goods;
	}

	private void sortGoodsBySalesVolume(List<Good> goods) {
		Collections.sort(goods, new Comparator<Good>() {
			@Override
			public int compare(Good o1, Good o2) {
				return o2.getSalesVolume() - o1.getSalesVolume();
			}
		});
	}

	private List<Good> getHotGoodsByClassfy(Classfy classfy) {
		// Tag hotTag = tagService.getTagByName(TagName.HOT);
		LOGGER.info("classfy id is {} name is {}, and supermarket is {}", classfy.getId(), classfy.getName(), classfy
				.getSupermarket().getName());
		List<Good> goods = new ArrayList<Good>();
		List<Good> findEntityByJpql = findEntityByJpql("From Good g where g.classfy = ? order by salesVolume desc",
				Arrays.asList(classfy), 0, 3);
		LOGGER.info("get {} goods with classfy id is {}", findEntityByJpql.size(), classfy.getId());
		goods.addAll(findEntityByJpql);
		List<Classfy> list = classfyService.getClassfiesByParent(classfy.getSupermarket(), classfy);
		LOGGER.info("get {} subclassfy with classfy id is {}", list.size(), classfy.getId());
		for (Classfy c : list) {
			goods.addAll(getHotGoodsByClassfy(c));
		}
		return goods;
	}

	@Override
	public List<Good> getAllDiscountGood() {
		List<Supermarket> all = supermarketService.getAll();
		List<Good> goods = new ArrayList<Good>();
		for (Supermarket supermarket : all) {
			goods.addAll(getAllDiscountGoodBySupermarket(supermarket));
		}
		return goods;
	}

}
