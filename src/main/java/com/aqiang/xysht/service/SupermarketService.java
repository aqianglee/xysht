package com.aqiang.xysht.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.ShopKeeper;
import com.aqiang.xysht.entities.Supermarket;

public interface SupermarketService extends BaseService<Supermarket> {

	public List<Supermarket> getSupermarketsByShopKeeper(ShopKeeper shopKeeper);

	public void saveOrUpdateEntity(Supermarket supermarket, MultipartFile p);

	public List<ErrorMessage> delete(Supermarket supermarket, Boolean noAsk);

}
