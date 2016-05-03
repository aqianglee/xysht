package com.aqiang.xysht.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqiang.xysht.dao.BaseDao;
import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.Manager;
import com.aqiang.xysht.service.ManagerService;
import com.aqiang.xysht.service.ValidateService;
import com.aqiang.xysht.utils.Md5Util;

@Service
@Transactional
public class ManagerServiceImpl extends BaseServiceImpl<Manager> implements
		ManagerService {

	@Autowired
	private ValidateService validateService;

	@Resource(name = "managerDao")
	@Override
	public void setDao(BaseDao<Manager> dao) {
		this.dao = dao;
	}

	@Override
	public Manager validateLoginInfo(Manager manager,
			List<ErrorMessage> errorMessages) {
		validateService.validate(errorMessages, manager.getUsername(),
				"username");
		validateService.validate(errorMessages, manager.getPassword(),
				"password");
		if (errorMessages.size() == 0) {
			String jpql = "From Manager s where s.username = ? or s.phone = ? or s.email = ?";
			List<Manager> ms = dao.findEntityByJpql(jpql,
					manager.getUsername(), manager.getUsername(),
					manager.getUsername());
			if (ms.isEmpty()) {
				errorMessages.add(new ErrorMessage("userNotRegister"));
			} else {
				if (ms.get(0).getPassword()
						.equals(Md5Util.md5(manager.getPassword()))) {
					return ms.get(0);
				} else {
					errorMessages.add(new ErrorMessage("passwordError"));
				}
			}
		}
		return null;
	}

	@Override
	public void register(Manager manager) {
		manager.setPassword(Md5Util.md5(manager.getPassword()));
		saveEntitiy(manager);
	}

}
