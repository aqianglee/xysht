package com.aqiang.xysht.service;

import java.util.List;

import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.Manager;

public interface ManagerService extends BaseService<Manager> {

	public Manager validateLoginInfo(Manager manager, List<ErrorMessage> info);

	public void register(Manager manager);

}
