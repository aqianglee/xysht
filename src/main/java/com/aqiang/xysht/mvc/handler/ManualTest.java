package com.aqiang.xysht.mvc.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aqiang.xysht.entities.Classfy;
import com.aqiang.xysht.entities.ClassfyLevel;
import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.Good;
import com.aqiang.xysht.entities.HotClassfyName;
import com.aqiang.xysht.entities.Manager;
import com.aqiang.xysht.entities.Parameter;
import com.aqiang.xysht.entities.ParameterKey;
import com.aqiang.xysht.entities.Picture;
import com.aqiang.xysht.entities.ReceiveAddress;
import com.aqiang.xysht.entities.ShopKeeper;
import com.aqiang.xysht.entities.Supermarket;
import com.aqiang.xysht.entities.Tag;
import com.aqiang.xysht.entities.TagName;
import com.aqiang.xysht.entities.User;
import com.aqiang.xysht.service.ClassfyService;
import com.aqiang.xysht.service.GoodService;
import com.aqiang.xysht.service.ManagerService;
import com.aqiang.xysht.service.ParameterService;
import com.aqiang.xysht.service.PictureService;
import com.aqiang.xysht.service.ReceiveAddressService;
import com.aqiang.xysht.service.ShopKeeperService;
import com.aqiang.xysht.service.SupermarketService;
import com.aqiang.xysht.service.TagService;
import com.aqiang.xysht.service.UserService;

@Controller
@RequestMapping("/nonLogin")
public class ManualTest {

	@Autowired
	private ShopKeeperService shopKeeperService;
	@Autowired
	private SupermarketService supermerketService;
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
	@Autowired
	private ManagerService managerService;
	@Autowired
	private UserService userService;
	@Autowired
	protected ReceiveAddressService receiveAddressService;

	@ResponseBody
	@RequestMapping("manualTest_prepareData")
	public String prepareData() {
		prepareManager();
		prepareParameter();
		ShopKeeper shopKeeper = registerShopKeeper("chendawen", "123", "青海大学学海路", "18826202524");
		Picture picture = createPicture("files/pictures/icon.jpg");
		Picture picture2 = createPicture("files/pictures/icon2.jpg");
		Supermarket supermarket = createNewSupermarket(shopKeeper, "学海超市", "学海路", picture);

		ShopKeeper shopKeeper2 = registerShopKeeper("ludawen", "123", "青大洗浴中心", "18826202524");
		Supermarket supermarket2 = createNewSupermarket(shopKeeper2, "青大超市", "青大洗浴中心", picture);

		ShopKeeper shopKeeper3 = registerShopKeeper("niudawen", "123", "青大洗浴中心", "18826202524");
		Supermarket supermarket3 = createNewSupermarket(shopKeeper3, "北极星电脑", "青大洗浴中心", picture2);

		Classfy clothes = createClassfy(supermarket, "衣服类", ClassfyLevel.LEVEL_ONE, null);
		Classfy snacks = createClassfy(supermarket, HotClassfyName.SNACK, ClassfyLevel.LEVEL_ONE, null);
		Classfy drinking = createClassfy(supermarket, HotClassfyName.DRINKING, ClassfyLevel.LEVEL_ONE, null);

		Classfy snacks2 = createClassfy(supermarket2, HotClassfyName.SNACK, ClassfyLevel.LEVEL_ONE, null);
		Classfy drinking2 = createClassfy(supermarket2, HotClassfyName.DRINKING, ClassfyLevel.LEVEL_ONE, null);
		Classfy electric = createClassfy(supermarket3, HotClassfyName.ELECTRIC, ClassfyLevel.LEVEL_ONE, null);

		Classfy phones = createClassfy(supermarket3, "手机", ClassfyLevel.LEVEL_TWO, electric);
		Classfy noteBooks = createClassfy(supermarket3, "笔记本", ClassfyLevel.LEVEL_TWO, electric);
		Classfy tablets = createClassfy(supermarket3, "平板电脑", ClassfyLevel.LEVEL_TWO, electric);
		Classfy pcs = createClassfy(supermarket3, "台式机", ClassfyLevel.LEVEL_TWO, electric);
		Classfy cameras = createClassfy(supermarket3, "照相机", ClassfyLevel.LEVEL_TWO, electric);
		Classfy coat = createClassfy(supermarket, "正装", ClassfyLevel.LEVEL_TWO, clothes);
		Classfy sport = createClassfy(supermarket, "运动服", ClassfyLevel.LEVEL_TWO, clothes);
		Classfy jeans = createClassfy(supermarket, "牛仔裤", ClassfyLevel.LEVEL_TWO, clothes);

		Classfy hots = createClassfy(supermarket, "辣条", ClassfyLevel.LEVEL_TWO, snacks);
		Classfy noodles = createClassfy(supermarket, "方便面", ClassfyLevel.LEVEL_TWO, snacks);

		Classfy hots2 = createClassfy(supermarket2, "辣条", ClassfyLevel.LEVEL_TWO, snacks2);
		Classfy noodles2 = createClassfy(supermarket2, "方便面", ClassfyLevel.LEVEL_TWO, snacks2);

		Classfy mi = createClassfy(supermarket3, "小米", ClassfyLevel.LEVEL_THREE, phones);
		Classfy huawei = createClassfy(supermarket3, "华为", ClassfyLevel.LEVEL_THREE, phones);
		Classfy sunxin = createClassfy(supermarket3, "三星", ClassfyLevel.LEVEL_THREE, phones);
		Classfy iphone = createClassfy(supermarket3, "苹果", ClassfyLevel.LEVEL_THREE, phones);
		Classfy meizu = createClassfy(supermarket3, "魅族", ClassfyLevel.LEVEL_THREE, phones);
		Classfy levone = createClassfy(supermarket3, "联想", ClassfyLevel.LEVEL_THREE, phones);
		Classfy coop = createClassfy(supermarket3, "酷派", ClassfyLevel.LEVEL_THREE, phones);
		Classfy jinli = createClassfy(supermarket3, "金立", ClassfyLevel.LEVEL_THREE, phones);
		Classfy zhongxin = createClassfy(supermarket3, "中兴", ClassfyLevel.LEVEL_THREE, phones);
		Classfy nubiya = createClassfy(supermarket3, "努比亚", ClassfyLevel.LEVEL_THREE, phones);
		Classfy nokia = createClassfy(supermarket3, "诺基亚", ClassfyLevel.LEVEL_THREE, phones);

		Tag hot = tagService.createTag(TagName.HOT);
		Tag discount = tagService.createTag(TagName.DISCOUNT);
		List<Tag> all = tagService.getAllTags();

		createGood("康师傅红烧牛肉面", supermarket, noodles, "1", "方便面", false, 3.5, "", all, 10);
		createGood("统一老坛酸菜牛肉面", supermarket, noodles, "1", "方便面", false, 3.5, "", all, 25);
		createGood("康师傅酸辣牛肉面", supermarket, noodles, "1", "方便面", false, 3.5, "", all, 7);
		createGood("康师傅鸡蛋面", supermarket, noodles, "1", "方便面", false, 3.5, "", all, 52);
		createGood("康师傅豉香排骨面", supermarket, noodles, "1", "方便面", false, 3.5, "", all, 38);

		createGood("康师傅红烧牛肉面(袋装)", supermarket2, noodles2, "1", "方便面", false, 3.5, "", all, 100);
		createGood("统一老坛酸菜牛肉面", supermarket2, noodles2, "1", "方便面", false, 3.5, "", all, 25);
		createGood("康师傅酸辣牛肉面", supermarket2, noodles2, "1", "方便面", false, 3.5, "", all, 25);
		createGood("康师傅鸡蛋面", supermarket2, noodles2, "1", "方便面", false, 3.5, "", all, 52);
		createGood("康师傅豉香排骨面", supermarket2, noodles2, "1", "方便面", false, 3.5, "", all, 38);

		createGood("好老婆", supermarket, hots, "1", "辣条", false, 2.5, "", null, 400);
		createGood("香香嘴", supermarket, hots, "1", "辣条", false, 2.5, "", null, 325);
		createGood("老爸", supermarket, hots, "1", "辣条", false, 2.5, "", null, 170);
		createGood("友友", supermarket, hots, "1", "辣条", false, 2.5, "", null, 230);

		createGood("唐僧肉", supermarket2, hots2, "1", "辣条", false, 2.5, "", null, 180);
		createGood("牛板筋", supermarket2, hots2, "1", "辣条", false, 2.5, "", null, 230);
		createGood("大刀肉", supermarket2, hots2, "1", "辣条", false, 2.5, "", null, 420);
		createGood("李小鹏", supermarket2, hots2, "1", "辣条", false, 2.5, "", null, 160);

		createGood("小米4", supermarket3, mi, "1", "手机", false, 1099D, "", all, 2003);
		createGood("小米4s", supermarket3, mi, "1", "手机", false, 1699D, "", all, 1);
		createGood("小米4c", supermarket3, mi, "1", "手机", false, 1299D, "", all, 1);
		createGood("小米5-标准版", supermarket3, mi, "1", "手机", false, 1999D, "", all, 50);
		createGood("小米5-高配版", supermarket3, mi, "1", "手机", false, 2299D, "", all, 1);
		createGood("小米5-尊贵版", supermarket3, mi, "1", "手机", false, 2699D, "", all, 8);
		createGood("红米note3", supermarket3, mi, "1", "手机", false, 999D, "", all, 1);
		createGood("红米3", supermarket3, mi, "1", "手机", false, 799D, "", all, 1);

		createGood("MX5", supermarket3, meizu, "1", "手机", false, 1399D, "", all, 1);
		createGood("MX6 PRO", supermarket3, meizu, "1", "手机", false, 2499D, "", all, 1);
		createGood("魅蓝note3", supermarket3, meizu, "1", "手机", false, 799D, "", all, 2000);
		createGood("魅蓝matel", supermarket3, meizu, "1", "手机", false, 799D, "", all, 1);

		createGood("华为荣耀6", supermarket3, huawei, "1", "手机", false, 1299D, "", all, 1);
		createGood("华为荣耀7", supermarket3, huawei, "1", "手机", false, 1999D, "", all, 1);
		createGood("华为荣耀4C", supermarket3, huawei, "1", "手机", false, 799D, "", all, 2888);
		createGood("华为荣耀4X", supermarket3, huawei, "1", "手机", false, 799D, "", all, 1);
		createGood("华为荣耀5C", supermarket3, huawei, "1", "手机", false, 999D, "", all, 1);
		createGood("华为荣耀P8", supermarket3, huawei, "1", "手机", false, 2699D, "", all, 1);
		createGood("华为荣耀P9", supermarket3, huawei, "1", "手机", false, 3299D, "", all, 1);
		createGood("华为荣耀P9-青春版", supermarket3, huawei, "1", "手机", false, 1899D, "", all, 1);

		createGood("三星Glaxy s7", supermarket3, sunxin, "1", "手机", false, 4299D, "", all, 1);
		createGood("三星Glaxy s7 edge", supermarket3, sunxin, "1", "手机", false, 5299D, "", all, 1);
		createGood("三星Glaxy s6", supermarket3, sunxin, "1", "手机", false, 3888D, "", all, 1);
		createGood("三星Glaxy s6 edge", supermarket3, sunxin, "1", "手机", false, 4888D, "", all, 1);

		Good good = createGood("nick", supermarket, sport, "1", "运动服", false, 130D, "", all, 1);
		createGood("pick", supermarket, sport, "1", "运动服", false, 130D, "", all, 1);
		createGood("x", supermarket, sport, "1", "运动服", false, 130D, "", all, 1);

		User user = new User();
		user.setEmail("695182311@qq.com");
		user.setPassword("123");
		user.setUsername("aqiang");
		user.setPhone("18826202524");
		ArrayList<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
		userService.registerUser(user, errorMessages);

		createReceiveAddress(user, "李志强", "青海大学学生公寓一号楼223", "18826202524", false);
		createReceiveAddress(user, "李志强", "广东省珠海市中建大厦1420", "18826202524", true);
		return "1";
	}

	private ReceiveAddress createReceiveAddress(User user, String compellation, String address, String phone,
			Boolean selected) {
		ReceiveAddress receiveAddress = new ReceiveAddress();
		receiveAddress.setUser(user);
		receiveAddress.setAddress(address);
		receiveAddress.setCompellation(compellation);
		receiveAddress.setPhone(phone);
		receiveAddress.setSelected(selected);
		receiveAddressService.saveEntitiy(receiveAddress);
		return receiveAddress;
	}

	private void prepareManager() {
		Manager manager = new Manager();
		manager.setUsername("aqianglee");
		manager.setPassword("123");
		manager.setEmail("2621338440@qq.com");
		manager.setCompellation("李志强");
		manager.setAddress("广东省珠海市拱北中建大厦1420");
		manager.setPhone("18826202524");
		managerService.register(manager);
	}

	private Good createGood(String name, Supermarket supermarket, Classfy classfy, String number, String description,
			Boolean offLine, Double price, String specification, List<Tag> tags, Integer salesVolume) {
		Good good = new Good();
		good.setName(name);
		good.setSupermarket(supermarket);
		good.setClassfy(classfy);
		good.setNumber(number);
		good.setDescription(description);
		good.setOffLine(offLine);
		good.setPrice(price);
		good.setSpecification(specification);
		good.setSalesVolume(salesVolume);
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

	private Supermarket createNewSupermarket(ShopKeeper shopKeeper, String name, String address, Picture icon) {
		Supermarket supermarket = new Supermarket();
		supermarket.setName(name);
		supermarket.setAddress(address);
		supermarket.setDescription("description");
		supermarket.setDespatchMoney(5D);
		supermarket.setBeginSendPrice(3D);
		supermarket.setIntroduction("introduction");
		supermarket.setShopKeeper(shopKeeper);
		supermarket.setNumber("1");
		supermarket.setQq("695182311");
		supermarket.setServiceArea("青大校园内");
		supermarket.setIcon(icon);
		supermerketService.saveEntitiy(supermarket);
		return supermarket;
	}

	private Picture createPicture(String path) {
		Picture icon = new Picture();
		icon.setPath(path);
		pictureService.saveEntitiy(icon);
		return icon;
	}

	private ShopKeeper registerShopKeeper(String username, String password, String address, String phone) {
		ShopKeeper shopKeeper = new ShopKeeper();
		shopKeeper.setUsername(username);
		shopKeeper.setPassword(password);
		shopKeeper.setAddress(address);
		shopKeeper.setPhone(phone);
		shopKeeperService.register(shopKeeper);
		return shopKeeper;
	}

}
