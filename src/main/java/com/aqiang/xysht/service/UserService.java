package com.aqiang.xysht.service;

import com.aqiang.xysht.entities.User;

public interface UserService extends BaseService<User> {

	public User updateUser(User user);

	public User getUserByPhone(String phone);

}
