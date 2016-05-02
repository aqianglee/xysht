package com.aqiang.xysht.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aqiang.xysht.entities.Classfy;
import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.Good;
import com.aqiang.xysht.entities.Supermarket;
import com.aqiang.xysht.entities.Tag;

public interface GoodService extends BaseService<Good> {
	public void updateGood(Good good, Integer[] tagsId, MultipartFile p);

	public List<Good> getAllGoodsByClassfy(Classfy classfy);

	public void delete(Good good);

	public void validateGood(Good good, List<ErrorMessage> messages);

	public void addTag(Good good, Tag tag);

	public void addTags(Good good, List<Tag> tags);

	public List<Good> getAllHotGoodBySupermarket(Supermarket supermarket);

	public void initTagsList(Good good);

	public List<Good> getAllDiscountGoodBySupermarket(Supermarket supermarket);

	public List<Good> getAllHotGood();

	public List<Good> getAllDiscountGood();
}
