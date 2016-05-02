package com.aqiang.xysht.mvc.handler;

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
import com.aqiang.xysht.entities.Picture;
import com.aqiang.xysht.entities.ShopKeeper;
import com.aqiang.xysht.entities.Supermarket;
import com.aqiang.xysht.entities.Tag;
import com.aqiang.xysht.entities.TagName;
import com.aqiang.xysht.service.ClassfyService;
import com.aqiang.xysht.service.GoodService;
import com.aqiang.xysht.service.ParameterService;
import com.aqiang.xysht.service.PictureService;
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
	@Autowired
	private PictureService pictureService;

	@ResponseBody
	@RequestMapping("manualTest_prepareData")
	public String prepareData() {
		prepareParameter();
		ShopKeeper shopKeeper = registerShopKeeper();
		Supermarket supermarket = createNewSupermarket(shopKeeper);
		Classfy electric = createClassfy(supermarket, "电器类", ClassfyLevel.LEVEL_ONE, null);
		Classfy clothes = createClassfy(supermarket, "衣服类", ClassfyLevel.LEVEL_ONE, null);
		Classfy snacks = createClassfy(supermarket, "零食类", ClassfyLevel.LEVEL_ONE, null);

		Classfy phones = createClassfy(supermarket, "手机", ClassfyLevel.LEVEL_TWO, electric);
		Classfy noteBooks = createClassfy(supermarket, "笔记本", ClassfyLevel.LEVEL_TWO, electric);
		Classfy tablets = createClassfy(supermarket, "平板电脑", ClassfyLevel.LEVEL_TWO, electric);
		Classfy pcs = createClassfy(supermarket, "台式机", ClassfyLevel.LEVEL_TWO, electric);
		Classfy cameras = createClassfy(supermarket, "照相机", ClassfyLevel.LEVEL_TWO, electric);
		Classfy coat = createClassfy(supermarket, "正装", ClassfyLevel.LEVEL_TWO, clothes);
		Classfy sport = createClassfy(supermarket, "运动服", ClassfyLevel.LEVEL_TWO, clothes);
		Classfy jeans = createClassfy(supermarket, "牛仔裤", ClassfyLevel.LEVEL_TWO, clothes);

		createClassfy(supermarket, "辣条", ClassfyLevel.LEVEL_TWO, snacks);
		createClassfy(supermarket, "方便面", ClassfyLevel.LEVEL_TWO, snacks);

		Classfy mi = createClassfy(supermarket, "小米", ClassfyLevel.LEVEL_THREE, phones);
		Classfy huawei = createClassfy(supermarket, "华为", ClassfyLevel.LEVEL_THREE, phones);
		Classfy sunxin = createClassfy(supermarket, "三星", ClassfyLevel.LEVEL_THREE, phones);
		Classfy iphone = createClassfy(supermarket, "苹果", ClassfyLevel.LEVEL_THREE, phones);
		Classfy meizu = createClassfy(supermarket, "魅族", ClassfyLevel.LEVEL_THREE, phones);
		Classfy levone = createClassfy(supermarket, "联想", ClassfyLevel.LEVEL_THREE, phones);
		Classfy coop = createClassfy(supermarket, "酷派", ClassfyLevel.LEVEL_THREE, phones);
		Classfy jinli = createClassfy(supermarket, "金立", ClassfyLevel.LEVEL_THREE, phones);
		Classfy zhongxin = createClassfy(supermarket, "中兴", ClassfyLevel.LEVEL_THREE, phones);
		Classfy nubiya = createClassfy(supermarket, "努比亚", ClassfyLevel.LEVEL_THREE, phones);
		Classfy nokia = createClassfy(supermarket, "诺基亚", ClassfyLevel.LEVEL_THREE, phones);

		Tag hot = tagService.createTag(TagName.HOT);
		Tag discount = tagService.createTag(TagName.DISCOUNT);
		List<Tag> tags = tagService.getAllTags();

		createGood("小米4", mi, "1", "手机", false, 1099D, "", tags);
		createGood("小米4s", mi, "1", "手机", false, 1699D, "", tags);
		createGood("小米4c", mi, "1", "手机", false, 1299D, "", tags);
		createGood("小米5-标准版", mi, "1", "手机", false, 1999D, "", tags);
		createGood("小米5-高配版", mi, "1", "手机", false, 2299D, "", tags);
		createGood("小米5-尊贵版", mi, "1", "手机", false, 2699D, "", tags);
		createGood("红米note3", mi, "1", "手机", false, 999D, "", tags);
		createGood("红米3", mi, "1", "手机", false, 799D, "", tags);

		createGood("MX5", meizu, "1", "手机", false, 1399D, "", tags);
		createGood("MX6 PRO", meizu, "1", "手机", false, 2499D, "", tags);
		createGood("魅蓝note3", meizu, "1", "手机", false, 799D, "", tags);
		createGood("魅蓝matel", meizu, "1", "手机", false, 799D, "", tags);

		createGood("华为荣耀6", huawei, "1", "手机", false, 1299D, "", tags);
		createGood("华为荣耀7", huawei, "1", "手机", false, 1999D, "", tags);
		createGood("华为荣耀4C", huawei, "1", "手机", false, 799D, "", tags);
		createGood("华为荣耀4X", huawei, "1", "手机", false, 799D, "", tags);
		createGood("华为荣耀5C", huawei, "1", "手机", false, 999D, "", tags);
		createGood("华为荣耀P8", huawei, "1", "手机", false, 2699D, "", tags);
		createGood("华为荣耀P9", huawei, "1", "手机", false, 3299D, "", tags);
		createGood("华为荣耀P9-青春版", huawei, "1", "手机", false, 1899D, "", tags);

		createGood("三星Glaxy s7", sunxin, "1", "手机", false, 4299D, "", tags);
		createGood("三星Glaxy s7 edge", sunxin, "1", "手机", false, 5299D, "", tags);
		createGood("三星Glaxy s6", sunxin, "1", "手机", false, 3888D, "", tags);
		createGood("三星Glaxy s6 edge", sunxin, "1", "手机", false, 4888D, "", tags);

		Good good = createGood("nick", sport, "1", "运动服", false, 130D, "", tags);
		createGood("pick", sport, "1", "运动服", false, 130D, "", tags);
		createGood("x", sport, "1", "运动服", false, 130D, "", tags);
		return "1";
	}

	private Good createGood(String name, Classfy classfy, String number, String description, Boolean offLine,
			Double price, String specification, List<Tag> tags) {
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

	private Classfy createClassfy(Supermarket supermarket, String name, String level, Classfy parent) {
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
		supermarket.setDescription("description");
		supermarket.setDespatchMoney(5D);
		supermarket.setBeginSendPrice(3D);
		supermarket.setIntroduction("introduction");
		supermarket.setShopKeeper(shopKeeper);
		supermarket.setNumber("1");
		supermarket.setQq("695182311");
		supermarket.setServiceArea(3);
		Picture icon = new Picture();
		icon.setPath("files/pictures/icon.jpg");
		pictureService.saveEntitiy(icon);
		supermarket.setIcon(icon);
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
