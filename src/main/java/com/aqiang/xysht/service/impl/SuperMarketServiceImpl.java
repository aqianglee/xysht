package com.aqiang.xysht.service.impl;

import java.util.ArrayList;
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
import com.aqiang.xysht.entities.ClassfyLevel;
import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.Picture;
import com.aqiang.xysht.entities.ShopKeeper;
import com.aqiang.xysht.entities.Supermarket;
import com.aqiang.xysht.service.ClassfyService;
import com.aqiang.xysht.service.PictureService;
import com.aqiang.xysht.service.ShopKeeperService;
import com.aqiang.xysht.service.SupermarketService;

@Service
@Transactional
public class SuperMarketServiceImpl extends BaseServiceImpl<Supermarket> implements SupermarketService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SuperMarketServiceImpl.class);

	@Autowired
	private ShopKeeperService shopKeeperService;
	@Autowired
	private PictureService pictureService;
	@Autowired
	private ClassfyService classfyService;

	@Override
	public List<Supermarket> getSupermarketsByShopKeeper(ShopKeeper shopKeeper) {
		return findEntityByJpql("From Supermarket where shopKeeper = ?", shopKeeper);
	}

	@Override
	public void saveOrUpdateEntity(Supermarket supermarket, MultipartFile p, String[] hotClass,
			MultipartFile activityPicture) {
		supermarket.setIcon(handlePicture(supermarket, p));
		supermarket.setActivityPicture(handlePicture(supermarket, activityPicture));
		Supermarket entity = margeEntity(supermarket);
		if (hotClass != null) {
			for (String string : hotClass) {
				Classfy classfy = new Classfy();
				classfy.setLevel(ClassfyLevel.LEVEL_ONE);
				classfy.setName(string);
				classfy.setSupermarket(supermarket);
				classfy.setSupermarket(entity);
				classfyService.saveEntitiy(classfy);
			}
		}
	}

	private Picture handlePicture(Supermarket supermarket, MultipartFile file) {
		if (file != null) {
			Picture icon = supermarket.getIcon();
			if (icon != null) {
				pictureService.deletePicture(icon);
			}
			return pictureService.savePicture(file);
		}
		return null;
	}

	@Resource(name = "superMarketDao")
	@Override
	public void setDao(BaseDao<Supermarket> dao) {
		this.dao = dao;
	}

	@Override
	public List<ErrorMessage> delete(Supermarket supermarket, Boolean noAsk) {
		List<ErrorMessage> messages = new ArrayList<ErrorMessage>();
		if (Boolean.TRUE.equals(noAsk)) {
			List<Classfy> classfies = classfyService.getClassfiesByParent(supermarket, null);
			if (!classfies.isEmpty()) {
				for (Classfy classfy : classfies) {
					classfyService.delete(classfy);
				}
			}
		} else {
			List<Classfy> classfies = classfyService.getClassfiesByParent(supermarket, null);
			if (!classfies.isEmpty()) {
				messages.add(new ErrorMessage("have.child"));
				return messages;
			}
		}
		deleteEntity(supermarket.getId());
		return messages;
	}
}
