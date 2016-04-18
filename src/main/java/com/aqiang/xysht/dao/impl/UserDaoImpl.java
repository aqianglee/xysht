package com.aqiang.xysht.dao.impl;

import org.springframework.stereotype.Repository;

import com.aqiang.xysht.dao.UserDao;
import com.aqiang.xysht.entities.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{
	
}
