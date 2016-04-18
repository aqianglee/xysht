package com.aqiang.xysht.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqiang.xysht.dao.BaseDao;
import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.ShopKeeper;
import com.aqiang.xysht.service.ShopKeeperService;
import com.aqiang.xysht.service.ValidateService;
import com.aqiang.xysht.utils.Md5Util;

@Service
@Transactional
public class ShopKeeperServiceImpl extends BaseServiceImpl<ShopKeeper> implements ShopKeeperService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ShopKeeperServiceImpl.class);
	@Autowired
	private ValidateService validateService;

	@Override
	@Resource(name = "shopKeeperDao")
	public void setDao(BaseDao<ShopKeeper> dao) {
		this.dao = dao;
	}

	@Override
	public ShopKeeper validateLoginInfo(ShopKeeper shopKeeper, List<ErrorMessage> errorMessages) {
		validateService.validate(errorMessages, shopKeeper.getUsername(), "username");
		validateService.validate(errorMessages, shopKeeper.getPassword(), "password");
		if (errorMessages.size() == 0) {
			String jpql = "From ShopKeeper s where s.username = ? or s.phone = ? or s.email = ?";
			List<ShopKeeper> ms = dao.findEntityByJpql(jpql, shopKeeper.getUsername(), shopKeeper.getUsername(),
					shopKeeper.getUsername());
			if (ms.isEmpty()) {
				errorMessages.add(new ErrorMessage("userNotRegister"));
			} else {
				if (ms.get(0).getPassword().equals(Md5Util.md5(shopKeeper.getPassword()))) {
					return ms.get(0);
				} else {
					errorMessages.add(new ErrorMessage("passwordError"));
				}
			}
		}
		return null;
	}

	@Override
	public void register(ShopKeeper shopKeeper) {
		shopKeeper.setPassword(Md5Util.md5(shopKeeper.getPassword()));
		saveEntitiy(shopKeeper);
	}

	@Override
	public List<ErrorMessage> validateRegister(ShopKeeper shopKeeper) {
		List<ErrorMessage> messages = new ArrayList<ErrorMessage>();
		validateService.validate(messages, shopKeeper.getUsername(), "username");
		validateService.validate(messages, shopKeeper.getPassword(), "password");
		validateService.validate(messages, shopKeeper.getRepassword(), "repassword");
		validateService.validate(messages, shopKeeper.getPhone(), "phone");
		validateService.validate(messages, shopKeeper.getEmail(), "email");
		if (messages.size() == 0) {
			validateService.validateUsernameBeenUsed(messages, shopKeeper.getUsername());
		}
		if (messages.size() == 0) {
			validateService
					.validatePasswordEqRepassword(messages, shopKeeper.getPassword(), shopKeeper.getRepassword());
		}
		return messages;
	}

	@Override
	public ShopKeeper getShopKeeperByUsername(String username) {
		List<ShopKeeper> keepers = findEntityByJpql("From ShopKeeper where username = ?", username);
		return keepers.isEmpty() ? null : keepers.get(0);
	}

}
