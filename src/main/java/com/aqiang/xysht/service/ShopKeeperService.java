package com.aqiang.xysht.service;

import java.util.List;

import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.ShopKeeper;

public interface ShopKeeperService extends BaseService<ShopKeeper> {

	public ShopKeeper validateLoginInfo(ShopKeeper shopKeeper, List<ErrorMessage> errorMessages);

	public void register(ShopKeeper shopKeeper);

	public List<ErrorMessage> validateRegister(ShopKeeper shopKeeper);

	public ShopKeeper getShopKeeperByUsername(String username);
	
}
