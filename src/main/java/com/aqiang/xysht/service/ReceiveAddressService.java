package com.aqiang.xysht.service;

import java.util.List;

import com.aqiang.xysht.entities.ReceiveAddress;
import com.aqiang.xysht.entities.User;

public interface ReceiveAddressService extends BaseService<ReceiveAddress> {

	List<ReceiveAddress> getReceiveAddressesByUser(User user);

}
