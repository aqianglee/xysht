package com.aqiang.xysht.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.ShopKeeper;
import com.aqiang.xysht.service.ShopKeeperService;
import com.aqiang.xysht.service.ValidateService;

@Service
public class ValidateServiceImpl implements ValidateService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidateServiceImpl.class);

	@Autowired
	private ShopKeeperService shopKeeperService;

	@Override
	public void validate(List<ErrorMessage> errorMessages, String field, String fieldName, Object... args) {
		LOGGER.info("validate with parameter string");
		if (StringUtils.isBlank(field)) {
			errorMessages.add(new ErrorMessage("missing." + fieldName, args));
		}
	}

	@Override
	public void validate(List<ErrorMessage> errorMessages, Date field, String fieldName, Object... args) {
		LOGGER.info("validate with parameter Date");
		if (field == null) {
			errorMessages.add(new ErrorMessage("missing." + fieldName, args));
		}
	}

	@Override
	public void validatePasswordEqRepassword(List<ErrorMessage> errorMessages, String password, String repassword) {
		if (!password.equals(repassword)) {
			errorMessages.add(new ErrorMessage("passwordNotEqRepassword"));
		}
	}

	@Override
	public void validateUsernameBeenUsed(List<ErrorMessage> errorMessages, String username) {
		ShopKeeper shopKeeper = shopKeeperService.getShopKeeperByUsername(username);
		if (shopKeeper != null) {
			errorMessages.add(new ErrorMessage("usernameBeenUsed"));
		}
	}

	@Override
	public void validate(List<ErrorMessage> errorMessages, Object field, String fieldName, Object... args) {
		LOGGER.info("validate with parameter object");
		if (field == null) {
			errorMessages.add(new ErrorMessage("missing." + fieldName, args));
		}
	}

}
