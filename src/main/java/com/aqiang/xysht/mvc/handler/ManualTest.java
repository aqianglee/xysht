package com.aqiang.xysht.mvc.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aqiang.xysht.entities.Classfy;
import com.aqiang.xysht.entities.ClassfyLevel;
import com.aqiang.xysht.entities.Good;
import com.aqiang.xysht.entities.Parameter;
import com.aqiang.xysht.entities.ParameterKey;
import com.aqiang.xysht.entities.ShopKeeper;
import com.aqiang.xysht.entities.Supermarket;
import com.aqiang.xysht.entities.Tag;
import com.aqiang.xysht.service.ClassfyService;
import com.aqiang.xysht.service.GoodService;
import com.aqiang.xysht.service.ParameterService;
import com.aqiang.xysht.service.ShopKeeperService;
import com.aqiang.xysht.service.SuperMarketService;
import com.aqiang.xysht.service.TagService;

@Controller
@RequestMapping("/nonLogin")
public class ManualTest {

	@Autowired
	private ShopKeeperService shopKeeperService;
	@Autowired
	private SuperMarketService supermerketService;
	@Autowired
	private ParameterService parameterService;
	@Autowired
	private ClassfyService classfyService;
	@Autowired
	private TagService tagService;
	@Autowired
	private GoodService goodService;

	@ResponseBody
	@RequestMapping("manualTest_prepareData")
	public String prepareData() {
		prepareParameter();
		ShopKeeper shopKeeper = registerShopKeeper();
		Supermarket supermarket = createNewSupermarket(shopKeeper);
		Classfy clothes = createClassfy(supermarket, "衣服类",
				ClassfyLevel.LEVEL_ONE, null);
		Classfy coat = createClassfy(supermarket, "外套", ClassfyLevel.LEVEL_TWO,
				clothes);
		Classfy sport = createClassfy(supermarket, "运动服",
				ClassfyLevel.LEVEL_THREE, coat);
		createClassfy(supermarket, "牛仔裤", ClassfyLevel.LEVEL_TWO, clothes);
		Classfy snacks = createClassfy(supermarket, "零食类",
				ClassfyLevel.LEVEL_ONE, null);
		createClassfy(supermarket, "辣条", ClassfyLevel.LEVEL_TWO, snacks);
		createClassfy(supermarket, "方便面", ClassfyLevel.LEVEL_TWO, snacks);
		Tag discount2 = tagService.createTag("两折");
		Tag discount3 = tagService.createTag("三折");
		Tag discount4 = tagService.createTag("四折");
		Tag discount5 = tagService.createTag("五折");
		Tag discount6 = tagService.createTag("六折");
		Tag discount8 = tagService.createTag("八折");
		List<Tag> tags = tagService.getAll();
		Good good = createGood("nick", sport, "1", "运动服", false, 130D, "", tags);
		return "1";
	}

	private Good createGood(String name, Classfy classfy, String number,
			String description, Boolean offLine, Double price,
			String specification, List<Tag> tags) {
		Good good = new Good();
		good.setName(name);
		good.setClassfy(classfy);
		good.setNumber(number);
		good.setDescription(description);
		good.setOffLine(offLine);
		good.setPrice(price);
		good.setSpecification(specification);
		goodService.addTags(good, tags);
		goodService.saveEntitiy(good);
		return good;
	}

	private void prepareParameter() {
		createParameter(ParameterKey.FILE_ROOT_DIR, "E://xysht/");
	}

	private void createParameter(String key, String value) {
		Parameter parameter = new Parameter();
		parameter.setKey(key);
		parameter.setValue(value);
		parameterService.saveEntitiy(parameter);
	}

	private Classfy createClassfy(Supermarket supermarket, String name,
			String level, Classfy parent) {
		Classfy classfy = new Classfy();
		classfy.setLevel(level);
		classfy.setSupermarket(supermarket);
		classfy.setName(name);
		classfy.setParent(parent);
		classfyService.saveEntitiy(classfy);
		return classfy;
	}

	private Supermarket createNewSupermarket(ShopKeeper shopKeeper) {
		Supermarket supermarket = new Supermarket();
		supermarket.setName("supermarket");
		supermarket.setAddress("guangdong zhuhai");
		supermarket.setBeginSendPrice(3D);
		supermarket.setShopKeeper(shopKeeper);
		supermerketService.saveEntitiy(supermarket);
		return supermarket;
	}

	private ShopKeeper registerShopKeeper() {
		ShopKeeper shopKeeper = new ShopKeeper();
		shopKeeper.setUsername("aqiang");
		shopKeeper.setPassword("123");
		shopKeeper.setAddress("zhuhai");
		shopKeeper.setPhone("18826202524");
		shopKeeperService.register(shopKeeper);
		return shopKeeper;
	}

}
