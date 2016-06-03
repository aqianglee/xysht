package com.aqiang.xysht.service;

import java.util.List;

import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.ReceiveAddress;
import com.aqiang.xysht.entities.User;

public interface UserService extends BaseService<User> {

	public User getUserByPhone(String phone);

	public User getUserByUsernameAndPassword(User user, List<ErrorMessage> errorMessages);

	public void registerUser(User user, List<ErrorMessage> errorMessages);

	public void addReceiveAddress(ReceiveAddress receiveAddress);

	public void setDefaultReceiveAddress(Integer userId, Integer receiveAddressId);

}
