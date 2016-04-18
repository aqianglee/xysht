package com.aqiang.xysht.dao.impl;

import org.springframework.stereotype.Repository;

import com.aqiang.xysht.dao.GoodDao;
import com.aqiang.xysht.entities.Good;

@Repository("goodDao")
public class GoodDaoImpl extends BaseDaoImpl<Good> implements GoodDao{

}
